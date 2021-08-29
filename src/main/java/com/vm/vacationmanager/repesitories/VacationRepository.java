package com.vm.vacationmanager.repesitories;

import com.vm.vacationmanager.models.entities.Vacation;
import com.vm.vacationmanager.models.views.VacationViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    @Query("select v from Vacation as v order by v.beginDate, v.user.username")
    List<Vacation>findOrdered ();

    @Query("select count(v.id) from Vacation as v join User  as u on v.user.id=u.id where u.username= :username and " +
            "(:beginDate between v.beginDate and v.endDate or :endDate between v.beginDate and v.endDate) and v.status<>'Rejected'")
    int isContainsDates(String username, LocalDate beginDate, LocalDate endDate);

    @Query("select v from Vacation as v join User  as u on v.user.id=u.id where v.status in :vacationStatus order by v.beginDate, v.endDate")
    List<Vacation> getVacationsByStatus(String[] vacationStatus);
}
