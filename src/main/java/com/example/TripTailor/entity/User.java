package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    private String password;

    @Column(nullable = false, length = 20)
    private String provider; // local / google / kakao / naver

    @Column(unique = true, length = 100)
    private String providerId; // 소셜 로그인 고유 ID

    private String nickname;
    private String profileImg;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
