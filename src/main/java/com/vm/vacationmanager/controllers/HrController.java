package com.vm.vacationmanager.controllers;

import com.vm.vacationmanager.services.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HrController {
    private final ModelMapper modelMapper;
    private final VacationService vacationService;

    public HrController(ModelMapper modelMapper, VacationService vacationService) {
        this.modelMapper = modelMapper;
        this.vacationService = vacationService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_HR')")
    public String myVacations(Model model) {
        if (!model.containsAttribute("vacationBindingModels")) {
            model.addAttribute("vacationBindingModels", vacationService.getAllVacations());
        }
        return "/vacation/list-vacations";
    }

}
