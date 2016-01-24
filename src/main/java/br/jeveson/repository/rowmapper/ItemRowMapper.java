package br.jeveson.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.jeveson.model.Item;

public class ItemRowMapper implements RowMapper<Item> {
	@Override
	public Item mapRow(ResultSet rs, int index) throws SQLException {
		Item item = new Item();
		
		item.setUser(rs.getString("user"));
		item.setCartId(rs.getInt("cart_id"));
		item.setProductId(rs.getString("product_id"));
		item.setName(rs.getString("name"));
		item.setQuantity(rs.getInt("quantity"));
		item.setTotal(rs.getBigDecimal("total"));
		
		return item;
	}
}
