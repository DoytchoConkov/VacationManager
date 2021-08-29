package com.vm.vacationmanager.services;

import com.vm.vacationmanager.models.binding.VacationBindingModel;
import com.vm.vacationmanager.models.entities.Vacation;
import com.vm.vacationmanager.models.views.VacationViewModel;

import java.util.List;

public interface VacationService {

     void save(VacationBindingModel vacation) ;

    List<VacationViewModel> getMyVacations();

    List<VacationViewModel> getAllVacations();

    void accept(Long id);

    void reject(Long id);

    void delete(Long id);

    void edit(Long id, VacationBindingModel vacationBindingModel);

    VacationBindingModel getById(Long id);

    boolean isContainsDates(String beginDate, String endDate);

    List<VacationViewModel> getVacationByStatus(String[] vacationStatus);
}
