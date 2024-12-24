package ru.sfedu.accounting.Models;

public class RepairService {
    private String name;
    private String address;

    // Геттеры
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
