package com.AppRh.RhProject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateDto {

    @NotEmpty
    @Size(max = 100, message = "the candidate name must have 100 characters max ")
    private String candidateName;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$", message = "Invalid CPF format. Expected XXX.XXX.XXX-XX")
    private String candidateCPF;

    @NotEmpty
    @Size(max = 100, message = "the candidate email must have 100 characters max")
    private String candidateEmail;

}
