package com.rail.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class RouteDTO {
	
	private Integer routeId;
	
	@NotEmpty(message = "Source cannot be empty, Please check")
	private String source;
	
	@NotEmpty(message = "Destination cannot be empty, Please check")
	private String destination;
	private List<TrainDTO> trainList;
	
	
	public RouteDTO() {
		
	}


	public Integer getRouteId() {
		return routeId;
	}


	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public List<TrainDTO> getTrainList() {
		return trainList;
	}


	public void setTrainList(List<TrainDTO> trainList) {
		this.trainList = trainList;
	}
	
	
	
}
