package com.formaciondbi.springboot.app.item.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.item.entities.Item;
import com.formaciondbi.springboot.app.item.entities.Producto;
import com.formaciondbi.springboot.app.item.services.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	
	@Autowired
	@Qualifier( "servicioRestTemplate")
	private ItemService itemService ;
	
	
	@GetMapping( "/listar" )
	public List<Item> allItems(){
		return itemService.findAll();	
	}
	
	@HystrixCommand( fallbackMethod = "metodoAlternativo" )
	@GetMapping( "/ver/{id}/cantidad/{count}" )
	public Item getItem( @PathVariable Long id, @PathVariable Integer count ) {
		return itemService.findById(id, count);
	}
	

	public Item metodoAlternativo(Long id, Integer cantidad){

		Item item = new Item();
		Producto producto = new Producto();

		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setName("Mac Studio");
		producto.setPrice(599.00);
		
		item.setProducto(producto);

		return item;
	}


}
