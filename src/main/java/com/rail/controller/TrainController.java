package com.rail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rail.service.TrainService;

@RestController
@RequestMapping()
public class TrainController {

	@Autowired
	TrainService trainService;
}
