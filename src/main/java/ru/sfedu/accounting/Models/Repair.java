package ru.sfedu.accounting.Models;

import java.util.Date;

public class Repair {
    private String individualNumber;
    private String repairService;
    private Date created;
    private Date updated;
    private String status;

    // Геттеры
    public String getIndividualNumber() {
        return individualNumber;
    }

    public String getRepairService() {
        return repairService;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getStatus() {
        return status;
    }

    // Сеттеры
    public void setIndividualNumber(String individualNumber) {
        this.individualNumber = individualNumber;
    }

    public void setRepairService(String repairService) {
        this.repairService = repairService;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
