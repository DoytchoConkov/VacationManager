package com.vm.vacationmanager.repesitories;

import com.vm.vacationmanager.models.entities.Roles;
import com.vm.vacationmanager.models.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByRole(Roles role);
}
