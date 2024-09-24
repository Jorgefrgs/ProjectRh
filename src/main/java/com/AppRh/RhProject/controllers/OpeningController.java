package com.AppRh.RhProject.controllers;

import com.AppRh.RhProject.dto.OpeningDto;
import com.AppRh.RhProject.models.Opening;
import com.AppRh.RhProject.repositories.OpeningRepository;
import com.AppRh.RhProject.services.OpeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/openings")
public class OpeningController {

    private final OpeningService openingService;
    private final OpeningRepository openingRepository;

    @GetMapping("/register")
    public ModelAndView showForm() {
        return new ModelAndView("opening/formOpening");
    }

    @PostMapping("/create")
    public ModelAndView createOpening(@ModelAttribute @Valid OpeningDto openingDTO, RedirectAttributes redirectAttributes) {
        openingService.createOpening(openingDTO);
        redirectAttributes.addFlashAttribute("message", "Opening created successfully!");
        return new ModelAndView("redirect:/openings"); // Redirecionar para a lista de openings
    }

    @GetMapping
    public ModelAndView listOpenings(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/opening/listOpening");
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
        return new ModelAndView("redirect:/openings"); // Redirecionar para a lista de openings
    }

    // Método para mostrar o formulário de atualização
    @GetMapping("/updateForm")
    public ModelAndView showUpdateFormPage() {
        OpeningDto openingDto = new OpeningDto(); // Cria um objeto vazio para o formulário
        ModelAndView modelAndView = new ModelAndView("opening/updateOpening");
        modelAndView.addObject("openingDto", openingDto);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateOpening(@ModelAttribute @Valid OpeningDto openingDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("opening/updateOpening");
        }

        openingService.updateOpening(openingDTO);

        redirectAttributes.addFlashAttribute("message", "Vaga atualizada com sucesso!");
        return new ModelAndView("redirect:/openings"); // Redirecionar para a lista de openings
    }

    // Método adicional para buscar e preencher o DTO com a vaga
    @PostMapping("/update/find")
    public ModelAndView findOpening(@RequestParam Long openingId, RedirectAttributes redirectAttributes) {
        Opening opening = openingService.findById(openingId);
        if (opening == null) {
            redirectAttributes.addFlashAttribute("message", "Vaga não encontrada!");
            return new ModelAndView("redirect:/openings/updateForm");
        }

        OpeningDto openingDto = new OpeningDto();
        openingDto.setOpeningId(opening.getOpeningId());
        openingDto.setOpeningName(opening.getOpeningName());
        openingDto.setOpeningDescription(opening.getOpeningDescription());
        openingDto.setOpeningSalary(opening.getOpeningSalary());
        openingDto.setOpeningDate(opening.getOpeningDate());

        ModelAndView modelAndView = new ModelAndView("opening/updateOpening");
        modelAndView.addObject("openingDto", openingDto);
        return modelAndView;
    }
}
