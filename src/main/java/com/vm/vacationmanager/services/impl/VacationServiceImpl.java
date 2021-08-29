package com.vm.vacationmanager.services.impl;

import com.vm.vacationmanager.models.binding.VacationBindingModel;
import com.vm.vacationmanager.models.entities.User;
import com.vm.vacationmanager.models.entities.Vacation;
import com.vm.vacationmanager.models.views.VacationViewModel;
import com.vm.vacationmanager.repesitories.VacationRepository;
import com.vm.vacationmanager.services.UserService;
import com.vm.vacationmanager.services.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;


    public VacationServiceImpl(VacationRepository vacationRepository, UserService userService, ModelMapper modelMapper) {
        this.vacationRepository = vacationRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(VacationBindingModel vacationModel) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Vacation vacation = modelMapper.map(vacationModel, Vacation.class);
        vacation.setUser(userService.getUserByUserName(username));
        vacation.setStatus("Pending...");
        vacationRepository.save(vacation);
    }

    @Override
    public List<VacationViewModel> getMyVacations() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userService.findMyVacations(username).stream()
                .map(v->modelMapper.map(v,VacationViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<VacationViewModel> getAllVacations() {
        return vacationRepository.findOrdered().stream().map(v->{
            VacationViewModel vacationView = modelMapper.map(v,VacationViewModel.class);
            vacationView.setUsername(v.getUser().getUsername());
            return vacationView;
        }).collect(Collectors.toList());
    }

    @Override
    public void accept(Long id) {
       Vacation vacation = vacationRepository.findById(id).orElseThrow();
       vacation.setStatus("Accepted");
       vacationRepository.save(vacation);
    }

    @Override
    public void reject(Long id) {
        Vacation vacation = vacationRepository.findById(id).orElseThrow();
        vacation.setStatus("Rejected");
        vacationRepository.save(vacation);
    }

    @Override
    public void delete(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public void edit(Long id, VacationBindingModel vacationBindingModel) {
        Vacation vacation = vacationRepository.findById(id).orElseThrow();
        vacation.setBeginDate(LocalDate.parse(vacationBindingModel.getBeginDate()));
        vacation.setEndDate(LocalDate.parse(vacationBindingModel.getEndDate()));
        vacation.setComment(vacationBindingModel.getComment());
        vacationRepository.save(vacation);
    }

    @Override
    public VacationBindingModel getById(Long id) {
        return modelMapper.map(vacationRepository.findById(id).orElseThrow(),VacationBindingModel.class);
    }

    @Override
    public boolean isContainsDates(String beginDate, String endDate) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return vacationRepository.isContainsDates(username,LocalDate.parse(beginDate),LocalDate.parse(endDate))>0;
    }
}
