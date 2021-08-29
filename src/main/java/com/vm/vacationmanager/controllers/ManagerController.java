package com.vm.vacationmanager.controllers;

import com.vm.vacationmanager.services.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final ModelMapper modelMapper;
    private final VacationService vacationService;

    public ManagerController(ModelMapper modelMapper, VacationService vacationService) {
        this.modelMapper = modelMapper;
        this.vacationService = vacationService;
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String myVacations(Model model) {
        if (!model.containsAttribute("vacationBindingModels")) {
            model.addAttribute("vacationBindingModels", vacationService.getAllVacations());
        }
        return "/vacation/manage-vacations";
    }

    @PostMapping("/accept/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String acceptVacation(@PathVariable Long id) {
        vacationService.accept(id);
        return "redirect:/manager/manage";
    }

    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String rejectVacation(@PathVariable Long id) {
        vacationService.reject(id);
        return "redirect:/manager/manage";
    }
}
