package com.rail.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rail.dto.TrainDTO;
import com.rail.service.TrainService;

@RestController
@RequestMapping("/trains")
@Validated
public class TrainController {

	@Autowired
	TrainService trainService;
	
	//For persisting train
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createTrain(@Valid @RequestBody TrainDTO trainDTO ) throws Exception{
		
		Integer trainId=trainService.createTrain(trainDTO);
		
		return ResponseEntity.ok("Train has been successfully created with id: "+trainId);
		
		
	}
	
	
	//for updating train fare
		@PutMapping(value = "/{trainId}",consumes = "application/json" )
		public ResponseEntity<String> updateFare(@PathVariable @Pattern(regexp = "[0-9]+") Integer trainId,@Valid @RequestBody TrainDTO trainDTO) throws Exception{

			
			trainService.updateFare(trainId,trainDTO);
		 
			return ResponseEntity.ok("Fare has been successfully updated with id: "+trainId);
			
		}
	
	
		//for getting train
		@GetMapping(value = "/{trainId}",produces = "application/json")
		public ResponseEntity<TrainDTO> getTrain(@PathVariable @Pattern(regexp = "[0-9]+") Integer trainId) throws Exception{
			
			TrainDTO t=trainService.getTrain(trainId);
			
			return ResponseEntity.ok(t);
		}
	
}
