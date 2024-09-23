package com.AppRh.RhProject.repositories;

import com.AppRh.RhProject.models.Candidate;
import com.AppRh.RhProject.models.Opening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByCandidateCPF(String CPF);
    List<Candidate> findByOpening(Opening opening);

}
