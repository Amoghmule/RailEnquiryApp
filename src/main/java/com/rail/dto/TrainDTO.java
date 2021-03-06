package com.rail.dto;

import java.time.LocalTime;


import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.PositiveOrZero;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;




public class TrainDTO {
	
	private Integer trainId;
	
	@NotEmpty(message = "{train.trainname.invalid}")
	private String trainName;
	
	
	@JsonDeserialize(using = ParseDeserializer.class)
	private LocalTime arrivalTime;
	
	
	@JsonDeserialize(using = ParseDeserializer.class)
	private LocalTime departureTime;
	
	@PositiveOrZero(message = "{train.fare.invalid}")
	private Double fare;

	
	public TrainDTO() {
		
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "TrainDTO [trainId=" + trainId + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", fare=" + fare + "]";
		
		
	} 
	
	
	
	
}
