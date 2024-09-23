package com.AppRh.RhProject.services;

import com.AppRh.RhProject.exceptions.BadRequestException;
import com.AppRh.RhProject.mappers.OpeningMapper;
import com.AppRh.RhProject.models.Opening;
import com.AppRh.RhProject.repositories.OpeningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class OpeningService {

    private final OpeningRepository openingRepository;
    private final OpeningMapper openingMapper;

    public Opening findByIdThrowBadRequestException(long openingId) {
        return openingRepository.findById(openingId)
                .orElseThrow(() -> new BadRequestException("Opening not found"));
    }

    public Page<Opening> listAll(Pageable pageable) {
        return openingRepository.findAll(pageable);
}
}
