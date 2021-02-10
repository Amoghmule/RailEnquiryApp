package com.rail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rail.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	RouteRepository routeRepository;
	
}
