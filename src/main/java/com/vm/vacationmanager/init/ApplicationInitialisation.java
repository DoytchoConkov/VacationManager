package com.vm.vacationmanager.init;

import com.vm.vacationmanager.models.entities.Roles;
import com.vm.vacationmanager.models.entities.UserRole;
import com.vm.vacationmanager.repesitories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitialisation implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;

    public ApplicationInitialisation(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) {
        if (userRoleRepository.count() == 0) {
            for (Roles r : Roles.values()) {
                UserRole userRole = new UserRole(r);
                userRoleRepository.save(userRole);
            }
        }
    }
}

