package com.AppRh.RhProject.services;

import com.AppRh.RhProject.dto.OpeningDto;
import com.AppRh.RhProject.exceptions.BadRequestException;
import com.AppRh.RhProject.models.Candidate;
import com.AppRh.RhProject.models.Opening;
import com.AppRh.RhProject.repositories.CandidateRepository;
import com.AppRh.RhProject.repositories.OpeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpeningService {
     private final OpeningRepository openingRepository;
     private final CandidateRepository candidateRepository;

    public Opening findById(Long openingId) {
        return openingRepository.findById(openingId)
                .orElseThrow(() -> new BadRequestException("Opening not found"));
    }

    public Page<Opening> listAll(Pageable pageable) {
        return openingRepository.findAll(pageable);
    }



    public void createOpening(OpeningDto openingDTO) {
        Opening opening = new Opening();
        opening.setOpeningName(openingDTO.getOpeningName());
        opening.setOpeningDescription(openingDTO.getOpeningDescription());
        opening.setOpeningDate(openingDTO.getOpeningDate());
        opening.setOpeningSalary(openingDTO.getOpeningSalary());
        openingRepository.save(opening);
    }

    public void deleteOpening(Long openingId) {
        Opening opening = findById(openingId);
        openingRepository.delete(opening);
    }

    public List<Candidate> findCandidatesByOpeningId(Long openingId) {
        Opening opening = findById(openingId);
        return candidateRepository.findByOpening(opening);
    }
    public void updateOpening(OpeningDto openingDTO) {
        Opening opening = findById(openingDTO.getOpeningId());

        opening.setOpeningName(openingDTO.getOpeningName());
        opening.setOpeningDescription(openingDTO.getOpeningDescription());
        opening.setOpeningSalary((openingDTO.getOpeningSalary()));
        opening.setOpeningDate(openingDTO.getOpeningDate());

        openingRepository.save(opening);
    }


}
