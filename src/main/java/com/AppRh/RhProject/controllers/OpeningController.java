package com.AppRh.RhProject.controllers;

import com.AppRh.RhProject.dto.OpeningDto;
import com.AppRh.RhProject.services.OpeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/openings")
public class OpeningController {

    private final OpeningService openingService;

    @GetMapping("/register")
    public ModelAndView showForm() {
        return new ModelAndView("opening/formOpening");
    }

    @PostMapping("/create")
    public ModelAndView createOpening(@ModelAttribute @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        openingService.createOpening(openingDTO);
        redirectAttributes.addFlashAttribute("message", "Opening created successfully!");
        return new ModelAndView("opening/formOpening");
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
    public ModelAndView deleteOpening(@PathVariable Long openingId, RedirectAttributes redirectAttributes) {
        openingService.deleteOpening(openingId);
        redirectAttributes.addFlashAttribute("message", "Opening deleted successfully!");
        return new ModelAndView("redirect:/openings");
    }

    @GetMapping("/update/{openingId}") // Método para mostrar o formulário de edição
    public ModelAndView showUpdateForm(@PathVariable Long openingId) {
        ModelAndView modelAndView = new ModelAndView("opening/updateOpening");
        modelAndView.addObject("opening", openingService.findById(openingId));
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView updateOpening(@ModelAttribute @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        openingService.updateOpening(openingDTO);
        redirectAttributes.addFlashAttribute("message", "Opening updated successfully!");
        return new ModelAndView("redirect:/openings");
    }
}
