package com.AppRh.RhProject.repositories;

import com.AppRh.RhProject.models.Opening;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpeningRepository extends JpaRepository<Opening, Long> {
    Opening findByOpeningId(Long openingId);
    List<Opening> findByOpeningName(String openingName);
}
