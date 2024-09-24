package com.AppRh.RhProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  candidateId;

    @NotEmpty
    private String candidateName;

    @NotEmpty
    @Column(unique = true)
    private String candidateCPF;

    @NotEmpty
    @Column(unique = true)
    private String candidateEmail;

    @ManyToOne
    private Opening opening;
}
