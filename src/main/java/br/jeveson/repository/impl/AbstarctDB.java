package br.jeveson.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AbstarctDB {

	private JdbcTemplate jdbcTemplateObject;
	
	public AbstarctDB(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/shopping_cart");
       dataSource.setUsername("webuser");
       dataSource.setPassword("webuser");
       this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}
	
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}
	
}
