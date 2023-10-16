package com.accenture.horsefeeding.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class FeedingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private FeedingSchedule feedingSchedule;
    private LocalTime feedingBegin;
    private LocalTime feedingEnd;
    private int amountConsumed;
    private String status;

    public FeedingRecord() {
    }

    public FeedingRecord(Long recordId, Horse horse, FeedingSchedule feedingSchedule, LocalTime feedingBegin, LocalTime feedingEnd, int amountConsumed, String status) {
        this.recordId = recordId;
        this.horse = horse;
        this.feedingSchedule = feedingSchedule;
        this.feedingBegin = feedingBegin;
        this.feedingEnd = feedingEnd;
        this.amountConsumed = amountConsumed;
        this.status = status;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    public LocalTime getFeedingBegin() {
        return feedingBegin;
    }

    public void setFeedingBegin(LocalTime feedingBegin) {
        this.feedingBegin = feedingBegin;
    }

    public LocalTime getFeedingEnd() {
        return feedingEnd;
    }

    public void setFeedingEnd(LocalTime feedingEnd) {
        this.feedingEnd = feedingEnd;
    }

    public int getAmountConsumed() {
        return amountConsumed;
    }

    public void setAmountConsumed(int amountConsumed) {
        this.amountConsumed = amountConsumed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

