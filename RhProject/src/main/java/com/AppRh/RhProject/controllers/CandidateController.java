package com.AppRh.RhProject.controllers;

import com.AppRh.RhProject.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @DeleteMapping("/{candidateCPF}")
    public String deleteCandidate(@PathVariable String candidateCPF) {
        candidateService.deleteCandidate(candidateCPF);
        return "redirect:/openings";
    }
}
