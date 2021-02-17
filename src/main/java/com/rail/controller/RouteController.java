package com.rail.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rail.dto.RouteDTO;
import com.rail.dto.TrainDTO;
import com.rail.service.RouteService;

import io.swagger.annotations.Api;



@RestController
@RequestMapping("/routes")
@Validated
@Api(value = "RouteController APIs")
public class RouteController {

	@Autowired
	RouteService routeService;
	
	private static final Logger logger= LoggerFactory.getLogger(RouteController.class);
	
	//For persisting Routes
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO) throws Exception{
		
		Integer routeId= routeService.createRoute(routeDTO);
		
		return ResponseEntity.ok("Route has been successfully created with id: "+routeId);
	}
	
	
	//For getting route
	@GetMapping(value =  "/{routeId}", produces = "application/json")
	public ResponseEntity<RouteDTO> getRoutebyId(@PathVariable @Positive Integer routeId) throws Exception{
		
		logger.debug("Inside Controller : getting route based on routeId: "+routeId);
		RouteDTO rDto=routeService.getRoutebyId(routeId);
		
		return ResponseEntity.ok(rDto);
	}
	
	
	//For train details by source and destination
	@GetMapping(value = "/trains",produces = "application/json")
	public ResponseEntity<List<TrainDTO>> getTrains(@RequestParam 
			@Pattern(regexp = "[a-zA-Z_-]+") String source,
			@RequestParam @Pattern(regexp = "[a-zA-Z_-]+") String destination ) throws Exception{
		
		logger.debug("Inside Controller : getting trains based on source: "+source+" and destination: "+destination);
		
		List<TrainDTO> tList=routeService.getTrains(source,destination);
		return ResponseEntity.ok(tList);
	
	}
	
	
	//for updating route
	@PutMapping(value = "/{routeId}", consumes = "application/json")
	public ResponseEntity<String> updateSourceAndDestination(@PathVariable @Positive Integer routeId,@Valid @RequestBody RouteDTO routeDTO
			) throws Exception{
		
		routeService.updateSourceAndDestination(routeDTO,routeId);
		
		return ResponseEntity.ok("Route has been successfully updated with id: "+routeId);	
	
	}
	
	
		//Remove train from Route
		@DeleteMapping("/{routeId}/trains/{trainId}")
		public ResponseEntity<String> deleteTrain(@PathVariable @Positive Integer routeId,@PathVariable @Positive Integer trainId) throws Exception{
			
			routeService.deleteTrainById(routeId,trainId);
			
			return ResponseEntity.ok("Train has been deleted successfully for route id: "+routeId);
			
		}
		
		
		
		
		//Add train in Route
		@PutMapping(value = "/{routeId}/trains", consumes = "application/json")
		public ResponseEntity<String> updateTrainDetails(@PathVariable @Positive Integer routeId,@Valid @RequestBody TrainDTO trainDTO) throws Exception{
			
			
			routeService.updateTrainDetails(trainDTO,routeId);

			
			return ResponseEntity.ok("Train has been added successfully for route Id: "+ routeId );
		}
	
	
	
}
