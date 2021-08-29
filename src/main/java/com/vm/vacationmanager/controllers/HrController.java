package com.vm.vacationmanager.controllers;

import com.vm.vacationmanager.services.UserService;
import com.vm.vacationmanager.services.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HrController {
    private final ModelMapper modelMapper;
    private final VacationService vacationService;
    private final UserService userService;

    public HrController(ModelMapper modelMapper, VacationService vacationService, UserService userService) {
        this.modelMapper = modelMapper;
        this.vacationService = vacationService;
        this.userService = userService;
    }

    @GetMapping("/vacation-list")
    @PreAuthorize("hasRole('ROLE_HR')")
    public String vacations(Model model) {
        if (!model.containsAttribute("vacationBindingModels")) {
            model.addAttribute("vacationBindingModels", vacationService.getAllVacations());
        }
        return "vacation/vacations-list";
    }

    @GetMapping("/users-list")
    @PreAuthorize("hasRole('ROLE_HR')")
    public String users(Model model) {
        if (!model.containsAttribute("userViewModels")) {
            model.addAttribute("userViewModels", userService.getAllUsers());
        }
        return "user/users-list";
    }
    @GetMapping("/user-details/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public String userDetails(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("userViewModel")) {
            model.addAttribute("userViewModel", userService.getUSerById(id));
        }
        return "user/user-details";
    }
}
