package com.rail.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rail.dto.RouteDTO;
import com.rail.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	RouteService routeService;
	
	//For persisting Routes
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO) throws Exception{
		
		Integer routeId= routeService.createRoute(routeDTO);
		
		return ResponseEntity.ok("Route has been successfully created with id: "+routeId);
	}
	
	
	
	
	
	
	
}
