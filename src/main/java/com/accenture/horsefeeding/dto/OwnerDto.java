package com.accenture.horsefeeding.dto;

public class OwnerDto {
    private Long ownerId;
    private String name;
    private String phone;

    public OwnerDto() {
    }

    public OwnerDto(Long ownerId, String name, String phone) {
        this.ownerId = ownerId;
        this.name = name;
        this.phone = phone;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

