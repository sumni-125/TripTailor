
USE triptailor_db;

-- 1. USER 테이블
/*
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255),
    provider VARCHAR(20) NOT NULL COMMENT 'local / google / kakao / naver',
    provider_id VARCHAR(100) UNIQUE COMMENT '소셜 로그인 고유 ID',
    nickname VARCHAR(50),
    profile_img VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
ALTER TABLE user
ADD COLUMN login_id VARCHAR(50) UNIQUE AFTER user_id;


ALTER TABLE user
MODIFY COLUMN email VARCHAR(100) UNIQUE;

ALTER TABLE user
ADD COLUMN role VARCHAR(20) DEFAULT 'ROLE_USER';
-- 2. TRIP 테이블
/*
CREATE TABLE trip (
    trip_cd BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    city VARCHAR(100) NOT NULL,
    title VARCHAR(100),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    CONSTRAINT fk_trip_user FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
-- 3. PLACE 테이블
/*
CREATE TABLE place (
    place_cd BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    latitude DECIMAL(10,7),
    longitude DECIMAL(10,7),
    category VARCHAR(50),
    link_url VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
-- 4. TRIP_DETAIL 테이블
/*
CREATE TABLE trip_detail (
    trip_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trip_cd BIGINT NOT NULL,
    travel_date DATE NOT NULL,
    place_cd BIGINT NOT NULL,
    order_no INT NOT NULL COMMENT '하루 내 방문 순서',
    start_time TIME,
    end_time TIME,
    memo TEXT,
    CONSTRAINT fk_tripdetail_trip FOREIGN KEY (trip_cd) REFERENCES trip(trip_cd) ON DELETE CASCADE,
    CONSTRAINT fk_tripdetail_place FOREIGN KEY (place_cd) REFERENCES place(place_cd) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
-- 5. SHARE_LINK 테이블
/*
CREATE TABLE share_link (
    share_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trip_cd BIGINT NOT NULL,
    share_uuid VARCHAR(100) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expired_at DATETIME,
    CONSTRAINT fk_sharelink_trip FOREIGN KEY (trip_cd) REFERENCES trip(trip_cd) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
-- 6. PDF_EXPORT 테이블
/*
CREATE TABLE pdf_export (
    pdf_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trip_cd BIGINT NOT NULL,
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pdf_trip FOREIGN KEY (trip_cd) REFERENCES trip(trip_cd) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
*/
/*
commit;

show tables;

select * from user;

DELETE from user
where user_id=2;

ALTER TABLE user MODIFY COLUMN user_id CHAR(36) PRIMARY KEY;
ALTER TABLE user AUTO_INCREMENT = 1;

USE triptailor_db;

*/

/*
-- 1. USER 테이블
CREATE TABLE user (
    user_cd CHAR(36) PRIMARY KEY,
    login_id VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50),
    role VARCHAR(20) DEFAULT 'ROLE_USER',
    provider VARCHAR(20) DEFAULT 'local',
    provider_id VARCHAR(100) UNIQUE,
    profile_img VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

CREATE TABLE trip (
    trip_cd CHAR(36) PRIMARY KEY,
    user_cd CHAR(36) NOT NULL,
    city VARCHAR(100) NOT NULL,
    title VARCHAR(100),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    CONSTRAINT fk_trip_user FOREIGN KEY (user_cd)
        REFERENCES user(user_cd) ON DELETE CASCADE
);

CREATE TABLE place (
    place_cd CHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    latitude DECIMAL(10,7),
    longitude DECIMAL(10,7),
    category VARCHAR(50),
    link_url VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE trip_detail (
    trip_detail_cd CHAR(36) PRIMARY KEY,
    trip_cd CHAR(36) NOT NULL,
    place_cd CHAR(36) NOT NULL,
    travel_date DATE NOT NULL,
    order_no INT NOT NULL,
    start_time TIME,
    end_time TIME,
    memo TEXT,
    CONSTRAINT fk_tripdetail_trip FOREIGN KEY (trip_cd)
        REFERENCES trip(trip_cd) ON DELETE CASCADE,
    CONSTRAINT fk_tripdetail_place FOREIGN KEY (place_cd)
        REFERENCES place(place_cd) ON DELETE CASCADE
);

-- 5. SHARE_LINK 테이블
CREATE TABLE share_link (
    share_id CHAR(36) PRIMARY KEY,                      -- UUID
    trip_cd CHAR(36) NOT NULL,
    share_uuid VARCHAR(100) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expired_at DATETIME,
    CONSTRAINT fk_sharelink_trip FOREIGN KEY (trip_cd)
        REFERENCES trip(trip_cd) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 6. PDF_EXPORT 테이블
CREATE TABLE pdf_export (
    pdf_id CHAR(36) PRIMARY KEY,                        -- UUID
    trip_cd CHAR(36) NOT NULL,
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pdf_trip FOREIGN KEY (trip_cd)
        REFERENCES trip(trip_cd) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

COMMIT;
*/
COMMIT;


CREATE TABLE share_link (
    share_id CHAR(36) PRIMARY KEY,
    trip_cd CHAR(36) NOT NULL,
    share_uuid VARCHAR(100) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expired_at DATETIME,
    CONSTRAINT fk_sharelink_trip FOREIGN KEY (trip_cd)
        REFERENCES trip(trip_cd) ON DELETE CASCADE
);

CREATE TABLE pdf_export (
    pdf_id CHAR(36) PRIMARY KEY,
    trip_cd CHAR(36) NOT NULL,
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pdf_trip FOREIGN KEY (trip_cd)
        REFERENCES trip(trip_cd) ON DELETE CASCADE
);


select * from user;