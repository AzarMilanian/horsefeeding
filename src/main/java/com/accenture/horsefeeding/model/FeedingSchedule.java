package com.accenture.horsefeeding.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class FeedingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    @OneToMany(mappedBy = "feedingSchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ScheduleRange> scheduleRanges;

    @OneToMany(mappedBy = "feedingSchedule")
    private List<FoodSchedule> foodSchedules;

    public FeedingSchedule() {
    }
    public FeedingSchedule(Horse horse, List<ScheduleRange> scheduleRanges) {
        this.horse = horse;
        this.scheduleRanges = scheduleRanges;
    }

    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public List<ScheduleRange> getScheduleRanges() {
        return scheduleRanges;
    }

    public void setScheduleRanges(List<ScheduleRange> scheduleRanges) {
        this.scheduleRanges = scheduleRanges;
    }


}
