package br.jeveson.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Product {
	
	public Product(){}
	
	public Product(String id,BigDecimal price,String name){
		this.id = id;
		this.price = price;
		this.name = name;
	}

	private String id = null;
	
	private BigDecimal price = null;
	
	private String name = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Item) {
			return ((Item)obj).getProductId().equals(this.id);
		}else if (obj instanceof String){
			return ((String)obj).equals(this.id);
		}
		
		return false;
	}

	
	
}
