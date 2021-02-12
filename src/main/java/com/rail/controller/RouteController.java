package com.rail.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

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



@RestController
@RequestMapping("/routes")
@Validated
public class RouteController {

	@Autowired
	RouteService routeService;
	
	//For persisting Routes
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO) throws Exception{
		
		Integer routeId= routeService.createRoute(routeDTO);
		
		return ResponseEntity.ok("Route has been successfully created with id: "+routeId);
	}
	
	
	//For getting route
	@GetMapping(value =  "/{routeId}", produces = "application/json")
	public ResponseEntity<RouteDTO> getRoutebyId(@PathVariable @Pattern(regexp = "[0-9]+") Integer routeId) throws Exception{
		
		RouteDTO rDto=routeService.getRoutebyId(routeId);
		
		return ResponseEntity.ok(rDto);
	}
	
	
	//For traindetails by source and destination
	@GetMapping(value = "/trains",produces = "application/json")
	public ResponseEntity<List<TrainDTO>> getTrains(@RequestParam 
			@Pattern(regexp = "[a-zA-Z]+") String source,
			@RequestParam @Pattern(regexp = "[a-zA-Z]+") String destination ) throws Exception{
		List<TrainDTO> tList=routeService.getTrains(source,destination);
		return ResponseEntity.ok(tList);
	
	}
	
	
	//for updating route
	@PutMapping(value = "/{routeId}", consumes = "application/json")
	public ResponseEntity<String> updateSourceAndDestination(@PathVariable @Pattern(regexp = "[0-9]+") Integer routeId,@Valid @RequestBody RouteDTO routeDTO
			) throws Exception{
		
		routeService.updateSourceAndDestination(routeDTO,routeId);
		
		return ResponseEntity.ok("Route has been successfully updated with id: "+routeId);	
	
	}
	
	
		//Remove train from Route
		@DeleteMapping("/{routeId}/{trainId}")
		public ResponseEntity<String> deleteTrain(@PathVariable @Pattern(regexp = "[0-9]+") Integer routeId,@PathVariable @Pattern(regexp = "[0-9]+") Integer trainId) throws Exception{
			
			routeService.deleteTrainById(routeId,trainId);
			
			return ResponseEntity.ok("Train has been deleted successfully for route id: "+routeId);
			
		}
		
		
		
		
		//Add train in Route
		@PutMapping(value = "/{routeId}/trains", consumes = "application/json")
		public ResponseEntity<String> updateTrainDetails(@PathVariable @Pattern(regexp = "[0-9]+") Integer routeId,@Valid @RequestBody TrainDTO trainDTO) throws Exception{
			
			
			routeService.updateTrainDetails(trainDTO,routeId);

			
			return ResponseEntity.ok("Train has been added successfully for route Id: "+ routeId );
		}
	
	
	
}
