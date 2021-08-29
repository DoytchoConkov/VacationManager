package com.vm.vacationmanager.models.views;

import java.util.List;

public class UserViewModel {
    private long id;
    private String username;
    private String email;
    private List<VacationViewModel> vacations;

    public UserViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<VacationViewModel> getVacations() {
        return vacations;
    }

    public void setVacations(List<VacationViewModel> vacations) {
        this.vacations = vacations;
    }
}
