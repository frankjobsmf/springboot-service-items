package com.formaciondbi.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formaciondbi.springboot.app.item.entities.Producto;

@FeignClient( name = "servicio-productos" )
public interface ProductoClienteRest {	
	
	@GetMapping("/listar")
	public List<Producto> products();

	@GetMapping( "/ver/{id}" )
	public Producto findProductById ( @PathVariable Long id );
	
}
