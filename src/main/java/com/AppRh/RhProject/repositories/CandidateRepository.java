package com.AppRh.RhProject.repositories;

import com.AppRh.RhProject.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
