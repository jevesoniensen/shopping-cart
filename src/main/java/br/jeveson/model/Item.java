package br.jeveson.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Item {
	
	private String productId = null;
	
	private int quantity = 0;
	
	private BigDecimal total = null;
	
	private String name = null;

	private String user = null;
	private int cartId = 0;
		
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String id) {
		this.productId = id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getCartId() {
		return cartId;
	}
	
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
}
