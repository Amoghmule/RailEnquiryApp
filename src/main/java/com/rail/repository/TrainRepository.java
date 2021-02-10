package com.rail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rail.entity.TrainEntity;

public interface TrainRepository extends  JpaRepository<TrainEntity, Integer>{

}
