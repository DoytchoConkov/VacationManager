package com.vm.vacationmanager.repesitories;

import com.vm.vacationmanager.models.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query("select v from Vacation as v order by v.beginDate, v.user.username")
    List<Vacation>findOrdered ();
}
