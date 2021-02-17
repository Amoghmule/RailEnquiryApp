package com.rail.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.rail.dto.TrainDTO;
import com.rail.entity.TrainEntity;
import com.rail.exception.BusinessException;
import com.rail.repository.TrainRepository;

@Service
public class TrainService {

	@Autowired
	TrainRepository trainRepository;
	
	@Autowired
	Environment environment;

	public Integer createTrain( TrainDTO trainDTO) {
		
		TrainEntity te1=trainRepository.saveAndFlush(copyDTOtoEntity(trainDTO));
		
		return te1.getTrainId();
	}
	
	
	
	
	private TrainEntity copyDTOtoEntity(TrainDTO trainDTO) {
		
		if(trainDTO==null) return null;
		
		TrainEntity te=new TrainEntity();
		te.setArrivalTime(trainDTO.getArrivalTime());
		te.setDepartureTime(trainDTO.getDepartureTime());
		te.setFare(trainDTO.getFare());
		te.setTrainName(trainDTO.getTrainName());
		
		
		return te;
		
	
	}
	
	
	
	
	public void updateFare(Integer trainId, TrainDTO trainDTO) throws Exception {
		
		
		Optional<TrainEntity> teOpt=trainRepository.findById(trainId);
		
		teOpt.orElseThrow(()-> new BusinessException(environment.getProperty("train.id.invalid")));
		
			
		trainRepository.updateFare(trainDTO.getFare(),trainId);
	
	}
	

	public TrainDTO getTrain( Integer trainId) throws Exception {
		
		Optional<TrainEntity> teOpt=trainRepository.findById(trainId);
		
		TrainEntity te= teOpt.orElseThrow(()-> new BusinessException(environment.getProperty("train.id.invalid"))) ;
		return copyEntityToDTO(te);
	}
	
	
	public void deleteTrainById(Integer trainId) throws Exception {
		Optional<TrainEntity> teOpt=trainRepository.findById(trainId);
		
		teOpt.orElseThrow(()-> new BusinessException(environment.getProperty("train.id.invalid"))) ;
		
		trainRepository.deleteById(trainId);
	}
	
	private TrainDTO copyEntityToDTO(TrainEntity te) {
		
		if(te==null) return null;
		
		TrainDTO t=new TrainDTO();
		t.setArrivalTime(te.getArrivalTime());
		t.setDepartureTime(te.getDepartureTime());
		t.setFare(te.getFare());
		t.setTrainName(te.getTrainName());
		t.setTrainId(te.getTrainId());
		
		return t;}




	




	




	
	
}
