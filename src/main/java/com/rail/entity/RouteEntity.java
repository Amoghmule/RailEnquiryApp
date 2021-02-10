package com.rail.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rail.dto.TrainDTO;


@Entity
@Table(name = "ROUTE")
public class RouteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROUTE_ID")
	private Integer routeId;
	private String source;
	private String destination;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Route_Train_Mapping",
			joinColumns = @JoinColumn(name="ROUTE_ID"),
			inverseJoinColumns = @JoinColumn(name="TRAIN_ID",unique = true))
	private List<TrainDTO> trainList;
	
	public RouteEntity() {
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
