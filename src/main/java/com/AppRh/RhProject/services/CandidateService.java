package com.AppRh.RhProject.services;

import com.AppRh.RhProject.exceptions.BadRequestException;
import com.AppRh.RhProject.models.Candidate;
import com.AppRh.RhProject.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public void deleteCandidate(String candidateCPF) {
        Candidate candidate = candidateRepository.findByCandidateCPF(candidateCPF);
        if (candidate == null) {
            throw new BadRequestException("Candidate not found");
        }
        candidateRepository.delete(candidate);
    }
}
