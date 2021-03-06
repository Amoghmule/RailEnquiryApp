package com.rail.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.rail.dto.TrainDTO;
import com.rail.service.TrainService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/trains")
@Validated
@Api(value = "TrainController APIs")
public class TrainController {

	@Autowired
	TrainService trainService;
	
	private static final Logger logger= LoggerFactory.getLogger(TrainController.class);
	
	//For persisting train
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createTrain(@Valid @RequestBody TrainDTO trainDTO ) throws Exception{
		
		Integer trainId=trainService.createTrain(trainDTO);
		
		return ResponseEntity.ok("Train has been successfully created with id: "+trainId);
		
		
	}
	
	
	//for updating train fare
		@PutMapping(value = "/{trainId}",consumes = "application/json" )
		public ResponseEntity<String> updateFare(@PathVariable @Positive(message = "{train.id.invalid}") Integer trainId,@Valid @RequestBody TrainDTO trainDTO) throws Exception{

			
			trainService.updateFare(trainId,trainDTO);
		 
			return ResponseEntity.ok("Fare has been successfully updated with id: "+trainId);
			
		}
	
	
		//for getting train
		@GetMapping(value = "/{trainId}",produces = "application/json")
		public ResponseEntity<TrainDTO> getTrain(@PathVariable @Positive(message = "{train.id.invalid}") Integer trainId) throws Exception{
			
			logger.debug("Inside Controller : getting train based on trainId: "+trainId);
			TrainDTO t=trainService.getTrain(trainId);
			
			return ResponseEntity.ok(t);
		}
		
		
		//for deleting train
		@DeleteMapping("/{trainId}")
		public ResponseEntity<String> deleteTrainById(@PathVariable @Positive(message = "{train.id.invalid}") Integer trainId) throws Exception{
			
			trainService.deleteTrainById(trainId);
			
			return ResponseEntity.ok("Train has been successfully deleted with id: "+trainId);
			
		}
	
}
