package com.AppRh.RhProject.controllers;

import com.AppRh.RhProject.dto.OpeningDto;
import com.AppRh.RhProject.models.Opening;
import com.AppRh.RhProject.repositories.CandidateRepository;
import com.AppRh.RhProject.repositories.OpeningRepository;
import com.AppRh.RhProject.services.OpeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RestController
@RequiredArgsConstructor
public class OpeningController {

    private final OpeningRepository openingRepository;
    private final OpeningService openingService;
    private final CandidateRepository candidateRepository;


    @GetMapping(value = "/openingRegister")
    public String showForm() {
        return "opening/formOpening";
    }

    @PostMapping(value = "/openingRegister")
    public String createOpening(@RequestBody @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        Opening opening = new Opening();
        opening.setOpeningName(openingDTO.getOpeningName());
        opening.setOpeningDescription(openingDTO.getOpeningDescription());
        opening.setOpeningDate(openingDTO.getOpeningDate());
        opening.setOpeningSalary(openingDTO.getOpeningSalary());

        openingRepository.save(opening);

        redirectAttributes.addFlashAttribute("message", "Opening created successfully!");
        return "redirect:/openingRegister";

    }

    @GetMapping("/openings")
    public ModelAndView listOpenings(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("opening/listOpening");
        var openings = openingRepository.findAll(pageable);
        modelAndView.addObject("openings", openings);
        return modelAndView;
    }

    @GetMapping(value = "/{openingId}")
    public ModelAndView openingDetails(@PathVariable Long openingId) {
        ModelAndView modelAndView = new ModelAndView("opening/openingDetails");

        Opening opening = openingService.findByIdThrowBadRequestException(openingId);

        modelAndView.addObject("opening", opening);
        var candidates = candidateRepository.findByOpening(opening);
        modelAndView.addObject("candidates", candidates);

        return modelAndView;
    }



}
