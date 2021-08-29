package com.vm.vacationmanager.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacations")
public class Vacation extends  BaseEntity{
    private LocalDate beginDate;
    private LocalDate endDate;
    private String comment;
    private String status;
    private User user;

    public Vacation() {
    }

    @Column(name = "begin_date", nullable = false)
    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate startDate) {
        this.beginDate = startDate;
    }

    @Column(name = "end_date", nullable = false)
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String resolution) {
        this.status = resolution;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}