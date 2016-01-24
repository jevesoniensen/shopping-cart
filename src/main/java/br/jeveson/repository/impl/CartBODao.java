package br.jeveson.repository.impl;

import br.jeveson.model.CartBO;

public interface CartBODao {

	CartBO addCart(String user);

	Integer getMaxCartId(String user);

	CartBO getCart(String user);

	int deleteCart(String user);

	int finishCart(String user);

}
