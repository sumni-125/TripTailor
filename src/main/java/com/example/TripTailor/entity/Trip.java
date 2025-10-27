package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_cd")
    private Long tripCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String city;

    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Integer totalDays;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
