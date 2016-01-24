package br.jeveson.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jeveson.model.Item;
import br.jeveson.repository.ItemDao;
import br.jeveson.repository.rowmapper.ItemRowMapper;

@Repository("ItemDao")
public class ItemDaoImpl extends AbstarctDB implements ItemDao {
		
	public ItemDaoImpl(){
       super();
	}
	
	public Item getItem(Item item){
		String sql = "SELECT user,cart_id,product_id,name,quantity,total FROM shopping_cart.item where user=? and product_id=? and cart_id=?";
		Object[] args = new Object[]{item.getUser(),item.getProductId(),item.getCartId()};
		List<Item> rs = getJdbcTemplateObject().query(sql, args ,new ItemRowMapper());
		
		if(rs.size() ==1){
			return rs.get(0);
		}
		
		return null;
	}
	
	public List<Item> getAllItem(Item item){
		String sql = "SELECT user,cart_id,product_id,name,quantity,total FROM shopping_cart.item where user=? and cart_id=?";
		Object[] args = new Object[]{item.getUser(),item.getCartId()};
		List<Item> rs = getJdbcTemplateObject().query(sql, args ,new ItemRowMapper());
		return rs;
	}
	
	public int save(Item item){
		String sql = "insert into shopping_cart.item (user,cart_id,product_id,name,quantity,total) values(?,?,?,?,?,?)";
		Object[] args = new Object[]{item.getUser(),item.getCartId(),item.getProductId(),item.getName(),item.getQuantity(),item.getTotal()};
		return getJdbcTemplateObject().update(sql, args);
	}
	
	public int update(Item item){
		String sql = "update shopping_cart.item set quantity =?,total=? where user=? and product_id=? and cart_id=?";
		Object[] args = new Object[]{item.getQuantity(),item.getTotal(),item.getUser(),item.getProductId(),item.getCartId()};
		return getJdbcTemplateObject().update(sql, args);
	}
	
	public int remove(Item item){
		String sql = "delete from shopping_cart.item where user=? and product_id=? and cart_id=?";
		Object[] args = new Object[]{item.getUser(),item.getProductId(),item.getCartId()};
		return getJdbcTemplateObject().update(sql, args);
	}
}
