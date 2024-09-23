package com.AppRh.RhProject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OpeningDto {

    private Long openingId;

    @NotEmpty(message = "the opening name cannot be empty")
    @Size(max = 100, message = "the opening name must have 100 chatacters max")
    private String openingName;

    @NotEmpty(message = "the opening description cannot be empty")
    @Size(max = 255, message = "the opening description must have 100 chatacters max")
    private String openingDescription;

    @NotEmpty(message = "the opening date cannot be empty")
    private String openingDate;

    @NotEmpty(message = "the opening salary cannot be empty")
    private long openingSalary;






}
