package com.AppRh.RhProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Opening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long openingId;

    @NotEmpty
    private String openingName;

    @NotEmpty
    private String openingDescription;

    @NotEmpty
    private String openingDate;

    @NotEmpty
    private long openingSalary;

    @OneToMany(mappedBy = "opening", cascade =  CascadeType.REMOVE)
    private List<Candidate> candidates = new ArrayList<>();


}
