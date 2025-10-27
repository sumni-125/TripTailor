package com.example.TripTailor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pdf_export")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdfExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdf_cd")
    private Long pdfId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_cd", nullable = false)
    private Trip trip;

    private String fileName;
    private String filePath;
    private LocalDateTime createdAt;
}
