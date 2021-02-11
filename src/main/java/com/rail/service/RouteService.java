package com.rail.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rail.dto.RouteDTO;
import com.rail.dto.TrainDTO;
import com.rail.entity.RouteEntity;
import com.rail.entity.TrainEntity;
import com.rail.exception.BusinessException;
import com.rail.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	RouteRepository routeRepository;

	public Integer createRoute( RouteDTO routeDTO) throws Exception {
		
		if(routeDTO==null) throw new BusinessException("route object cannot be null, Please check");
		
		RouteEntity routeEntityResult= routeRepository.saveAndFlush(copyDTOtoEntity(routeDTO));
		
		
		return routeEntityResult.getRouteId();
	}
	
	
	private RouteEntity copyDTOtoEntity(RouteDTO routeDTO) {
		
		if(routeDTO==null) return null;
			
		RouteEntity re=new RouteEntity();
		re.setSource(routeDTO.getSource());
		re.setDestination(routeDTO.getDestination());
		List<TrainEntity> teList=new ArrayList<>();
		if(routeDTO.getTrainList()!=null && !routeDTO.getTrainList().isEmpty()) {
			for(TrainDTO t:routeDTO.getTrainList()) {
				TrainEntity te=new TrainEntity();
				te.setTrainName(t.getTrainName());
				te.setArrivalTime(t.getArrivalTime());
				te.setDepartureTime(t.getDepartureTime());
				te.setFare(t.getFare());
				teList.add(te);
			}			
		}
		re.setTrainList(teList);
		
		
		
		return re;
		
	}
}
