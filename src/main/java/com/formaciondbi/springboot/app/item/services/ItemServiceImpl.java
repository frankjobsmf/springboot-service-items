package com.formaciondbi.springboot.app.item.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formaciondbi.springboot.app.item.entities.Item;
import com.formaciondbi.springboot.app.item.entities.Producto;


@Service("servicioRestTemplate")
public class ItemServiceImpl implements ItemService {

	
	@Autowired
	public RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		
		List<Producto> productos = Arrays.asList(  clienteRest.getForObject("http://servicio-productos/listar", Producto[].class) );
		
		return productos.stream().map( producto -> new Item( producto, 2 )).collect( Collectors.toList() );
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		Map<String, String>  pathVariables = new HashMap<String, String>();
		
		pathVariables.put("id", id.toString());
		
		Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);		
		
		Item item = new Item( producto, cantidad );
		
		return item;
	}

}
