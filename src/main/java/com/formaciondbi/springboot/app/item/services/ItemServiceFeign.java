package com.formaciondbi.springboot.app.item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formaciondbi.springboot.app.item.clients.ProductoClienteRest;
import com.formaciondbi.springboot.app.item.entities.Item;

@Service( "feignCliente" )
@Primary()
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.products().stream().map( producto -> new Item( producto, 2 ) ).collect(  Collectors.toList() );
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		return new Item( clienteFeign.findProductById(id), cantidad );
	}

}
