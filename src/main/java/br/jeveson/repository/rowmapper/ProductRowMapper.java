package br.jeveson.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.jeveson.model.Product;

public class ProductRowMapper implements RowMapper<Product>{
	
	@Override
	public Product mapRow(ResultSet rs, int index) throws SQLException {
		Product p = new Product();
		
		p.setId(rs.getString("id"));
		p.setPrice(rs.getBigDecimal("price"));
		p.setName(rs.getString("name"));
		
		return p;
	}
}
