package com.example.TripTailor.repository;

import com.example.TripTailor.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUserCdOrderByCreatedAtDesc(String userCd);

}
