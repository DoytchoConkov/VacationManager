package com.vm.vacationmanager.models.binding;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class VacationBindingModel {
    private String beginDate;
    private String endDate;
    private String comment;

    public VacationBindingModel() {
    }

    @NotBlank(message = "Begin date can not be empty.")
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    @NotBlank(message = "End date can not be empty.")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
