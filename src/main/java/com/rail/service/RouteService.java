package com.rail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	Environment environment;

	public Integer createRoute(RouteDTO routeDTO) throws Exception {

		if (routeDTO == null)
			throw new BusinessException(environment.getProperty("route.object.must"));

		RouteEntity routeEntityResult = routeRepository.saveAndFlush(copyDTOtoEntity(routeDTO));

		return routeEntityResult.getRouteId();
	}

	public RouteDTO getRoutebyId(Integer routeId) throws Exception {

		Optional<RouteEntity> reOpt = routeRepository.findById(routeId);

		RouteEntity reEntity = reOpt.orElseThrow(() -> new BusinessException(environment.getProperty("route.id.invalid")) );

		return copyEntitytoDTO(reEntity);
	}

	public List<TrainDTO> getTrains(String source, String destination) throws Exception {

		List<RouteEntity> reList = routeRepository.findBySourceAndDestination(source, destination);
		List<TrainDTO> tList = new ArrayList<>();

		if (reList == null)
			throw new BusinessException(environment.getProperty("route.object.must"));

		for (RouteEntity re : reList) {
			RouteDTO rDto = copyEntitytoDTO(re);
			tList.addAll(rDto.getTrainList());
		}

		return tList;
	}

	public void updateSourceAndDestination(RouteDTO routeDTO, Integer routeId) throws Exception {

		routeRepository.findById(routeId)
				.orElseThrow(() -> new BusinessException(environment.getProperty("route.id.invalid")));

		routeRepository.updateSourceAndDestination(routeDTO.getSource(), routeDTO.getDestination(), routeId);

	}

	public void deleteTrainById(Integer routeId, Integer trainId) throws Exception {

		Optional<RouteEntity> reOpt = routeRepository.findById(routeId);

		RouteEntity re = reOpt.orElseThrow(() -> new BusinessException(environment.getProperty("route.id.invalid")));

		List<TrainEntity> teList = re.getTrainList().stream().filter(x -> x.getTrainId() != trainId)
				.collect(Collectors.toList());

		re.setTrainList(teList);

		routeRepository.saveAndFlush(re);

	}

	public void updateTrainDetails( TrainDTO trainDTO,  Integer routeId) throws Exception {
		Optional<RouteEntity> reOpt=routeRepository.findById(routeId);
		
			RouteEntity re=reOpt.orElseThrow(() -> new BusinessException(environment.getProperty("route.id.invalid")));
			
			
			List<TrainEntity> teList=re.getTrainList();
			
			TrainEntity te=new TrainEntity();
			te.setTrainName(trainDTO.getTrainName());
			te.setArrivalTime(trainDTO.getArrivalTime());
			te.setDepartureTime(trainDTO.getDepartureTime());
			te.setFare(trainDTO.getFare());
			
			if(trainDTO.getTrainId()!=null)
			te.setTrainId(trainDTO.getTrainId());
			
			teList.add(te);
			re.setTrainList(teList);
			
			routeRepository.saveAndFlush(re);
		
		
	}

	private RouteDTO copyEntitytoDTO(RouteEntity rEntity) {

		if (rEntity == null)
			return null;

		RouteDTO r = new RouteDTO();
		r.setRouteId(rEntity.getRouteId());
		r.setDestination(rEntity.getDestination());
		r.setSource(rEntity.getSource());
		
		List<TrainDTO> tList = new ArrayList<>();
		List<TrainEntity> teList = rEntity.getTrainList();

		if (teList != null && teList.size() != 0) {
			for (TrainEntity te : teList) {
				TrainDTO t = new TrainDTO();
				t.setArrivalTime(te.getArrivalTime());
				t.setDepartureTime(te.getDepartureTime());
				t.setTrainId(te.getTrainId());
				t.setFare(te.getFare());
				t.setTrainName(te.getTrainName());
				tList.add(t);

			}
		}
		r.setTrainList(tList);

		return r;

	}

	private RouteEntity copyDTOtoEntity(RouteDTO routeDTO) {

		if (routeDTO == null)
			return null;

		RouteEntity re = new RouteEntity();
		re.setSource(routeDTO.getSource());
		re.setDestination(routeDTO.getDestination());
		
		List<TrainEntity> teList = new ArrayList<>();
		if (routeDTO.getTrainList() != null && !routeDTO.getTrainList().isEmpty()) {
			for (TrainDTO t : routeDTO.getTrainList()) {
				TrainEntity te = new TrainEntity();
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
