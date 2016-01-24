package br.jeveson.repository;

import java.util.List;

import br.jeveson.model.Product;

public interface ProductsDao {

	List<Product> getProducts();

	Product getProduct(String id);

}
