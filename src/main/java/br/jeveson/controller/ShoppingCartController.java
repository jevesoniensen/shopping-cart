package br.jeveson.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.jeveson.model.Cart;
import br.jeveson.model.CartBO;
import br.jeveson.model.Item;
import br.jeveson.model.Product;
import br.jeveson.repository.CartBODao;
import br.jeveson.repository.ItemDao;
import br.jeveson.repository.ProductsDao;



@ComponentScan("br.jeveson.model,br.jeveson.repository.impl")
@EnableWebMvc
@Controller
public class ShoppingCartController {
	
	@Autowired
	private ProductsDao productsDao;
	
	@Autowired
	private CartBODao cartBODao;
	
	@Autowired
	private ItemDao itemDao;
		
	@RequestMapping(value = "/checkSession", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> checkSession(HttpServletRequest request){
		
		String user = (String)request.getSession().getAttribute("user");
		
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("logged", String.valueOf(user!=null&&user.trim().length()>0));
		rs.put("user", user);
		
		return rs;
	}
	
	@RequestMapping(value = "/signin" , method = RequestMethod.POST)
	public @ResponseBody Map<String, String> signin(@RequestBody String user,HttpServletRequest request){
				
		request.getSession().setAttribute("user", user);
				
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("logged", String.valueOf(user!=null&&user.trim().length()>0));
		rs.put("user", user);
		
		return rs;
	}
	
	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public @ResponseBody Map<String, String> logout(@RequestBody String user,HttpServletRequest request){
				
		HttpSession session = request.getSession(false);
		
		if(session != null){
			session.removeAttribute("user");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("logged", "false");
		rs.put("user", null);			
		return rs;
	}
	
	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts(){
		return getProductsDao().getProducts();
	}
	
	@RequestMapping(value = "/{user}/addItem" , method = RequestMethod.POST)
	public @ResponseBody Cart add(@PathVariable String user,@RequestBody Item item){
		
		CartBO cartBO = cartBODao.getCart(user);
		if(cartBO == null){
			cartBO = cartBODao.addCart(user);
		}
		item.setUser(user);
		item.setCartId(cartBO.getId());
				
		Product product = getProductsDao().getProduct(item.getProductId());
				
		item.setName(product.getName());
		
		Item dbItem = getItemDao().getItem(item);
		
		if(dbItem == null){
			item.setTotal(new BigDecimal(product.getPrice().doubleValue() * item.getQuantity()));
			getItemDao().save(item);
		}else{
			item.setQuantity(dbItem.getQuantity()+item.getQuantity());
			item.setTotal(item.getTotal().add(dbItem.getTotal()));
			getItemDao().update(item);
		}
		
		return preparCart(item);
	}
	
	@RequestMapping(value = "/{user}/updateItem" , method = RequestMethod.PUT)
	public @ResponseBody Cart update(@PathVariable String user,@RequestBody Item item){
		item.setUser(user);	
		CartBO cartBO = cartBODao.getCart(user);
		item.setCartId(cartBO.getId());
		
		Product product = getProductsDao().getProduct(item.getProductId());
		item.setTotal(new BigDecimal(product.getPrice().doubleValue() * item.getQuantity()));
		getItemDao().update(item);
		
		return preparCart(item);
	}
	
	@RequestMapping(value = "/{user}/removeItem" , method = RequestMethod.POST)
	public @ResponseBody Cart removeItem(@PathVariable String user,@RequestBody String id){
		CartBO cartBO = cartBODao.getCart(user);
		Item item = new Item();
		item.setProductId(id);
		item.setUser(user);
		item.setCartId(cartBO.getId());
		getItemDao().remove(item);
				
		return preparCart(item);
	}
	
	@RequestMapping(value = "/{user}/cancelCart" , method = RequestMethod.DELETE)
	public @ResponseBody Cart cancelCart(@PathVariable String user){
		CartBO cartBO = cartBODao.getCart(user);
		Item item = new Item();
		item.setUser(user);
		item.setCartId(cartBO.getId());
		List<Item> items = itemDao.getAllItem(item);
		
		for (Item itemX : items) {
			getItemDao().remove(itemX);
		}
		
		cartBODao.deleteCart(user);
				
		return new Cart();
	}
	
	@RequestMapping(value = "/{user}/finishCart" , method = RequestMethod.PUT)
	public @ResponseBody Cart finishCart(@PathVariable String user){
		
		cartBODao.finishCart(user);
				
		return new Cart();
	}
	
	@RequestMapping(value ="/{user}/getCart", method = RequestMethod.GET)
	public @ResponseBody Cart getCart(@PathVariable String user){
		CartBO cartBO = cartBODao.getCart(user);
		
		if(cartBO == null){
			return new Cart();
		}
		
		Item item = new Item();
		item.setUser(user);
		item.setCartId(cartBO.getId());
		
		return preparCart(item);
	}
	
	public ProductsDao getProductsDao() {
		return productsDao;
	}
	
	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}
	
	public CartBODao getCartBODao() {
		return cartBODao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setCartBODao(CartBODao cartBODao) {
		this.cartBODao = cartBODao;
	}
	
	public Cart preparCart(Item item){
		List<Item> items = itemDao.getAllItem(item);
		
		BigDecimal total = new BigDecimal(0);
		for (Item itemX : items) {
			total = total.add(itemX.getTotal());
		}
		
		Cart cart = new Cart();
		cart.setItems(items);
		cart.setTotal(total);
		
		return cart;
	}

}
