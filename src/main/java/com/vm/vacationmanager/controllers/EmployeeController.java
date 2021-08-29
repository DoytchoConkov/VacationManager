package com.vm.vacationmanager.controllers;

import com.vm.vacationmanager.models.binding.VacationBindingModel;
import com.vm.vacationmanager.services.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final VacationService vacationService;

    public EmployeeController(ModelMapper modelMapper, VacationService vacationService) {
        this.modelMapper = modelMapper;
        this.vacationService = vacationService;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String createVacation(Model model) {
        if (!model.containsAttribute("vacationBindingModel")) {
            model.addAttribute("vacationBindingModel", new VacationBindingModel());
        }
        return "/vacation/create-vacation";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String createVacationConfirm(@Valid @ModelAttribute("vacationBindingModel")
                                                VacationBindingModel vacationBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("vacationBindingModel", vacationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vacationBindingModel",
                    bindingResult);
            return "redirect:/employee/create";
        }

        if (LocalDate.parse(vacationBindingModel.getBeginDate()).compareTo(LocalDate.parse(vacationBindingModel.getEndDate())) > 0) {
            redirectAttributes.addFlashAttribute("errorDates", true);
            redirectAttributes.addFlashAttribute("vacationBindingModel", vacationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vacationBindingModel",
                    bindingResult);
            return "redirect:/employee/create";
        }
        if (vacationService.isContainsDates(vacationBindingModel.getBeginDate(),vacationBindingModel.getEndDate())) {
            redirectAttributes.addFlashAttribute("dateCrossing", true);
            redirectAttributes.addFlashAttribute("vacationBindingModel", vacationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vacationBindingModel",
                    bindingResult);
            return "redirect:/employee/create";
        }

        vacationService.save(vacationBindingModel);
        return "redirect:/employee/view";
    }

    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String myVacations(Model model) {
        if (!model.containsAttribute("vacationBindingModels")) {
            model.addAttribute("vacationBindingModels", vacationService.getMyVacations());
        }
        return "/vacation/my-vacations";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String deleteVacation(@PathVariable Long id) {
        vacationService.delete(id);
        return "redirect:/employee/view";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String editVacation(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("vacationBindingModel")) {
            model.addAttribute("_id", id);
            model.addAttribute("vacationBindingModel", vacationService.getById(id));
        }
        return "/vacation/edit-vacation";
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String editSparePart(@PathVariable Long id, @ModelAttribute VacationBindingModel vacationBindingModel) {
        vacationService.update(id, vacationBindingModel);
        return "redirect:/employee/view";
    }
}
