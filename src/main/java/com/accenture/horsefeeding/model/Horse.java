package com.accenture.horsefeeding.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "horse")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horseId;
    @Column(name = "guid", unique = true, nullable = false)
    private String guid;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "breed")
    private String breed;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "stable_id")
    @JsonManagedReference
    private Stable stable;

    public Horse() {
    }

    public Horse(Long horseId, String guid, String officialName, String nickname, String breed, Owner owner, Stable stable) {
        this.horseId = horseId;
        this.guid = guid;
        this.officialName = officialName;
        this.nickname = nickname;
        this.breed = breed;
        this.owner = owner;
        this.stable = stable;
    }

    public Horse(String guid, String officialName) {
        this.guid = guid;
        this.officialName = officialName;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Stable getStable() {
        return stable;
    }

    public void setStable(Stable stable) {
        this.stable = stable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(horseId, horse.horseId) &&
                Objects.equals(guid, horse.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horseId, guid);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "horseId=" + horseId +
                ", guid='" + guid + '\'' +
                ", officialName='" + officialName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", breed='" + breed + '\'' +
                ", owner=" + (owner == null ? "null" : owner.getOwnerId()) +
                ", stable=" + (stable == null ? "null" : stable.getStableId()) +
                '}';
    }

}
