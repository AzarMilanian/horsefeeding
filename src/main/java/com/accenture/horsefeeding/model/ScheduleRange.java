package com.accenture.horsefeeding.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class ScheduleRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rangeId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private FeedingSchedule feedingSchedule;

    private LocalTime feedingRangeStart;
    private LocalTime feedingRangeEnd;

    public ScheduleRange() {
    }

    public ScheduleRange(Long rangeId, FeedingSchedule feedingSchedule, LocalTime feedingRangeStart, LocalTime feedingRangeEnd) {
        this.rangeId = rangeId;
        this.feedingSchedule = feedingSchedule;
        this.feedingRangeStart = feedingRangeStart;
        this.feedingRangeEnd = feedingRangeEnd;
    }

    public LocalTime getFeedingRangeStart() {
        return feedingRangeStart;
    }

    public LocalTime getFeedingRangeEnd() {
        return feedingRangeEnd;
    }

    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    public Long getRangeId() {
        return rangeId;
    }

    public void setRangeId(Long rangeId) {
        this.rangeId = rangeId;
    }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    public void setFeedingRangeStart(LocalTime feedingRangeStart) {
        this.feedingRangeStart = feedingRangeStart;
    }

    public void setFeedingRangeEnd(LocalTime feedingRangeEnd) {
        this.feedingRangeEnd = feedingRangeEnd;
    }
}

