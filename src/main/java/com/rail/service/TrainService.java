package com.rail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rail.repository.TrainRepository;

@Service
public class TrainService {

	@Autowired
	TrainRepository trainRepository;
	
}
