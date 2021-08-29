package com.vm.vacationmanager.services;

import com.vm.vacationmanager.models.binding.UserRolesBindingModel;
import com.vm.vacationmanager.models.entities.User;
import com.vm.vacationmanager.models.entities.Vacation;
import com.vm.vacationmanager.models.service.UserServiceModel;
import com.vm.vacationmanager.models.views.UserViewModel;


import java.io.IOException;
import java.util.List;

public interface UserService {
    void registerUserAndLogin(UserServiceModel userServiceModel) throws IOException;

    boolean findUserByUserName(String username);

    User getUserByUserName(String springSecurityFormUsernameKey);

    List<UserViewModel> getUsers();

    void setRoles(UserRolesBindingModel userRolesBindingModel);

    boolean isMoreOneAdmin();

    List<Vacation> findMyVacations(String username);
}
