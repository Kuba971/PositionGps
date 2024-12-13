package com.gddj.gps.repository;

import com.gddj.gps.entity.GpsPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsPositionRepository extends JpaRepository<GpsPositionEntity, Long> {
}
