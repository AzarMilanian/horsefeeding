package com.accenture.horsefeeding.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Stable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stableId;
    private String stableName;

    @OneToMany(mappedBy = "stable")
    @JsonBackReference
    private List<Horse> horses;

    public Stable() {
    }

    public Stable(Long stableId, String stableName) {
        this.stableId = stableId;
        this.stableName = stableName;
    }

    public Stable(Long stableId, String stableName, List<Horse> horses) {
        this.stableId = stableId;
        this.stableName = stableName;
        this.horses = horses;
    }

    public Long getStableId() {
        return stableId;
    }

    public void setStableId(Long stableId) {
        this.stableId = stableId;
    }

    public String getStableName() {
        return stableName;
    }

    public void setStableName(String stableName) {
        this.stableName = stableName;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }
}
