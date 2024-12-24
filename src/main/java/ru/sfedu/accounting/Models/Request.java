package ru.sfedu.accounting.Models;

import java.util.Date;
import java.util.UUID;

public class Request {
    private UUID id;
    private String body;
    private String status;
    private Date created;
    private Date updated;
    private String ownerINN;
    private String managerINN;

    // Геттеры
    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getOwnerINN() {
        return ownerINN;
    }

    public String getManagerINN() {
        return managerINN;
    }

    // Сеттеры
    public void setId(UUID id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setOwnerINN(String ownerINN) {
        this.ownerINN = ownerINN;
    }

    public void setManagerINN(String managerINN) {
        this.managerINN = managerINN;
    }
}
