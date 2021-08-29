package com.vm.vacationmanager.repesitories;

import com.vm.vacationmanager.models.entities.User;
import com.vm.vacationmanager.models.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User as u where u.username=:username")
    Optional<User> findByUsername(String username);

    @Query("select count(u.id) from User as u JOIN u.roles as r WHERE r.role=1")
    int findAdminUsers();

    @Query("select u from User as u order by u.username")
    List<User> findAll();

    @Query("select v from Vacation  as v join User as u on v.user.id= u.id where u.username= :username order by v.beginDate,v.endDate desc ")
    List<Vacation> findMyVacations(String username);

    @Query("select u from User as u where u.email=:email")
    Optional<User> findByEmail(String email);
}
