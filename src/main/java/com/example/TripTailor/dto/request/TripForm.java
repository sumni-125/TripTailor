package com.example.TripTailor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TripForm {

    @NotBlank
    private String city;

    private String title;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String visibility = "PRIVATE";
    // PRIVATE / LINK / PUBLIC
}