package br.jeveson.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jeveson.model.Product;
import br.jeveson.repository.ProductsDao;
import br.jeveson.repository.rowmapper.ProductRowMapper;

@Repository("productsDao")
public class ProductsDaoImpl extends AbstarctDB implements ProductsDao {
	
	public ProductsDaoImpl(){
	       super();
	}
	
	public List<Product> getProducts(){
		String sql = "SELECT id,name,price FROM shopping_cart.product;";
		
		List<Product> rs = getJdbcTemplateObject().query(sql, new ProductRowMapper());
		
		return rs;
	}
	
	public Product getProduct(String id){
		String sql = "SELECT id,name,price FROM shopping_cart.product where id=?";
		Object[] args = new Object[]{id};
		Product rs = getJdbcTemplateObject().queryForObject(sql,args, new ProductRowMapper());		
		return rs;
	}
	
	
	public static void main (String[] args){
		ProductsDaoImpl pd = new ProductsDaoImpl();
		pd.getProducts();
	}

}
