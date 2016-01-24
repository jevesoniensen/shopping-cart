package br.jeveson.repository;

import java.util.List;

import br.jeveson.model.Item;

public interface ItemDao {

	Item getItem(Item item);

	int save(Item item);

	int update(Item item);

	List<Item> getAllItem(Item item);

	int remove(Item item);

}
