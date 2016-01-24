package br.jeveson.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jeveson.model.CartBO;
import br.jeveson.repository.CartBODao;
import br.jeveson.repository.rowmapper.CartBORowMapper;

@Repository("CartBODao")
public class CartBODaoImpl extends AbstarctDB implements CartBODao {
	
	public CartBODaoImpl(){
	       super();
	}
	
	public CartBO getCart(String user){
		String sql = "SELECT id,user FROM shopping_cart.cart where user=? and status=?";
		Object[] args = new Object[]{user,"WAITING"};
		List<CartBO> list = getJdbcTemplateObject().query(sql, args ,new CartBORowMapper());
		
		if(list.size() == 1){
			return list.get(0);
		}
		
		return null;
	}
	
	public int deleteCart(String user){
		String sql = "delete FROM shopping_cart.cart where user=? and status=?";
		Object[] args = new Object[]{user,"WAITING"};
		return getJdbcTemplateObject().update(sql, args);
	}
	
	public int finishCart(String user){
		String sql = "update shopping_cart.cart set status=? where user=? and status=?";
		Object[] args = new Object[]{"FINISHED",user,"WAITING"};
		return getJdbcTemplateObject().update(sql, args);
	}
	
	public Integer getMaxCartId(String user){
		String sql = "SELECT max(id) FROM shopping_cart.cart where user=? and status=?";
		Object[] args = new Object[]{user,"FINISHED"};
		return getJdbcTemplateObject().queryForObject(sql, args, Integer.class);
	}
	
	public CartBO addCart(String user){
		String sql = "insert into shopping_cart.cart (user,id,status) values(?,?,?)";
		Integer max = getMaxCartId(user);
		int cartId = max!=null?max.intValue()+1:1;
		Object[] args = new Object[]{user,cartId,"WAITING"};
		getJdbcTemplateObject().update(sql,args);
		
		CartBO cartBO = new CartBO();
		cartBO.setUser(user);
		cartBO.setId(cartId);
		return cartBO;
		
	}

}
