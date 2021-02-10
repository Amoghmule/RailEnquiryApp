package com.rail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rail.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Integer>{
	
	List<RouteEntity> findBySourceAndDestination(String source,String destination);
	
	
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically = true)
	@Query("update RouteEntity r SET source=?1 ,destination=?2 WHERE r.routeId=?3")
	void updateSourceAndDestination(String source,String destination,Integer routeId );
}
