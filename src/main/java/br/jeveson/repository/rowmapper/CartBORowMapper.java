package br.jeveson.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.jeveson.model.CartBO;

public class CartBORowMapper implements RowMapper<CartBO> {
	
	@Override
	public CartBO mapRow(ResultSet rs, int index) throws SQLException  {
		CartBO cartBO = new CartBO();
		
		cartBO.setId(rs.getInt("id"));
		cartBO.setUser(rs.getString("user"));
		
		return cartBO;
	}

}
