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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_cd", nullable = false, updatable = false, length = 36)
    private String userCd;

    @Column(name = "login_id", unique = true, length = 50)
    private String loginId;

    @Column(unique = true, length = 100)
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

    @Column(nullable = false)
    private String role = "ROLE_USER";
}
