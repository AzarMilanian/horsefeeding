package com.accenture.horsefeeding.dto;

public class HorseDto {
    private Long horseId;
    private String guid;
    private String officialName;
    private String nickname;
    private String breed;
    private Long ownerId;
    private Long stableId;

    public HorseDto() {
    }

    public HorseDto(Long horseId, String guid, String officialName, String nickname, String breed, Long ownerId, Long stableId) {
        this.horseId = horseId;
        this.guid = guid;
        this.officialName = officialName;
        this.nickname = nickname;
        this.breed = breed;
        this.ownerId = ownerId;
        this.stableId = stableId;
    }

    public Long getHorseId() {
        return horseId;
    }

    public void setHorseId(Long horseId) {
        this.horseId = horseId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getStableId() {
        return stableId;
    }

    public void setStableId(Long stableId) {
        this.stableId = stableId;
    }
}

