package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trip_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_detail_cd")
    private Long tripDetailCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_cd", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_cd", nullable = false)
    private Place place;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false)
    private Integer orderNo; // 하루 내 방문 순서

    private LocalTime startTime;
    private LocalTime endTime;

    @Lob
    private String memo;
}
