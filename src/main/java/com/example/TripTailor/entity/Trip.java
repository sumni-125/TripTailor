package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "trip")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String city;

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}