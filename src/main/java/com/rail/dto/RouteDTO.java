package com.rail.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RouteDTO {
	
	private Integer routeId;
	
	@NotEmpty(message = "{route.source.invalid}")
	@Pattern(regexp = "[a-zA-Z_-]+", message = "{route.source.format}")
	private String source;
	
	@NotEmpty(message = "{route.destination.invalid}")
	@Pattern(regexp = "[a-zA-Z_-]+", message = "{route.destination.format}")
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


	@Override
	public String toString() {
		return "RouteDTO [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", trainList="
				+ trainList + "]";
	}
	
	
	
	
}
