package com.example.TripTailor.service;

import com.example.TripTailor.dto.request.TripForm;
import com.example.TripTailor.entity.Trip;
import com.example.TripTailor.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<Trip> getMyTrips(String userCd){
        return tripRepository.findByUserCdOrderByCreatedAtDesc(userCd);
    }

    public Trip createTrip(String userCd, TripForm form) {
        Trip trip = new Trip();
        trip.setUserCd(userCd);
        trip.setCity(form.getCity());
        trip.setTitle(form.getTitle());
        trip.setStartDate(form.getStartDate());
        trip.setEndDate(form.getEndDate());

        // totalDays 계산 (end - start + 1)
        long days = ChronoUnit.DAYS.between(form.getStartDate(), form.getEndDate()) + 1;
        trip.setTotalDays((int) days);

        // 공개 범위
        trip.setVisibility(form.getVisibility());

        trip.setCreatedAt(LocalDateTime.now());
        trip.setUpdatedAt(LocalDateTime.now());

        return tripRepository.save(trip);
    }

}
