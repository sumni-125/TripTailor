package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "place")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeCd;

    @Column(nullable = false, length = 100)
    private String name;

    private String address;
    private Double latitude;
    private Double longitude;
    private String category;
    private String linkUrl;

    private LocalDateTime createdAt;
}