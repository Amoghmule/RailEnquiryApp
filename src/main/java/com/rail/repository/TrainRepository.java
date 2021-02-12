package com.rail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rail.entity.TrainEntity;

public interface TrainRepository extends  JpaRepository<TrainEntity, Integer>{

	@Transactional(readOnly = false)
	@Modifying(clearAutomatically = true)
	@Query("update TrainEntity t SET fare=?1 WHERE t.trainId=?2")
	void updateFare(Double fare, Integer trainId);

}
