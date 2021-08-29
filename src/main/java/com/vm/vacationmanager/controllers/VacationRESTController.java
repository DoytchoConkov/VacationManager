package com.vm.vacationmanager.controllers;

import com.vm.vacationmanager.models.views.VacationViewModel;
import com.vm.vacationmanager.services.VacationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacations")
public class VacationRESTController {
    private final VacationService vacationService;

    public VacationRESTController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/filter-vacation")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public List<VacationViewModel> getAllModelsForBrand(@RequestParam String[] vacationStatus) {
        return vacationService.getVacationByStatus(vacationStatus);
    }
}
