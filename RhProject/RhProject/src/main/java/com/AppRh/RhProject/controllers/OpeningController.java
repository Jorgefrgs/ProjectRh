package com.AppRh.RhProject.controllers;

import com.AppRh.RhProject.dto.OpeningDto;
import com.AppRh.RhProject.models.Opening;
import com.AppRh.RhProject.repositories.OpeningRepository;
import com.AppRh.RhProject.services.OpeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RestController
@RequestMapping("/openings")
public class OpeningController {

    private final OpeningService openingService;

    @GetMapping("/register")
    public String showForm() {
        return "opening/formOpening";
    }

    @PostMapping("/register")
    public String createOpening(@RequestBody @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        openingService.createOpening(openingDTO);
        redirectAttributes.addFlashAttribute("message", "Opening created successfully!");
        return "redirect:/openings/register";
    }

    @GetMapping
    public ModelAndView listOpenings(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("opening/listOpening");
        modelAndView.addObject("openings", openingService.listAll(pageable));
        return modelAndView;
    }

    @GetMapping("/{openingId}")
    public ModelAndView openingDetails(@PathVariable Long openingId) {
        ModelAndView modelAndView = new ModelAndView("opening/openingDetails");
        modelAndView.addObject("opening", openingService.findById(openingId));
        modelAndView.addObject("candidates", openingService.findCandidatesByOpeningId(openingId));
        return modelAndView;
    }

    @DeleteMapping("/{openingId}")
    public String deleteOpening(@PathVariable Long openingId) {
        openingService.deleteOpening(openingId);
        return "redirect:/openings";
    }

    @PutMapping(value = "/updateOpening")
    public String updateOpening(@RequestBody @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        Opening updatedOpening = openingService.updateOpening(openingDTO);
        redirectAttributes.addFlashAttribute("message", "Opening updated successfully!");
        return "redirect:/" + updatedOpening.getOpeningId();
    }

}
