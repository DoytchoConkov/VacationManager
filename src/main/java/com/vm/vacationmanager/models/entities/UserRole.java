package com.vm.vacationmanager.models.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    public UserRole() {
    }

    public UserRole(Roles role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
