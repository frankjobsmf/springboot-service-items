package com.formaciondbi.springboot.app.item.services;

import java.util.List;

import com.formaciondbi.springboot.app.item.entities.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
	
}
