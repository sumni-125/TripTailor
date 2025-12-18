package com.example.TripTailor.repository;

import com.example.TripTailor.entity.TripDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TripDetailRepository extends JpaRepository<TripDetail, Long> {
    List<TripDetail> findByTripCdAndUserCd(Long tripCd, String userCd);
}
