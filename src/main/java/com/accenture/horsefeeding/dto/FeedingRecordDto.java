package com.accenture.horsefeeding.dto;

import java.time.LocalTime;

public class FeedingRecordDto {
    private Long recordId;
    private Long horseId;
    private Long feedingScheduleId;
    private LocalTime feedingBegin;
    private LocalTime feedingEnd;
    private String status;
    private int amountConsumed;

    public FeedingRecordDto() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getHorseId() {
        return horseId;
    }

    public void setHorseId(Long horseId) {
        this.horseId = horseId;
    }

    public Long getFeedingScheduleId() {
        return feedingScheduleId;
    }

    public void setFeedingScheduleId(Long feedingScheduleId) {
        this.feedingScheduleId = feedingScheduleId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmountConsumed() {
        return amountConsumed;
    }

    public void setAmountConsumed(int amountConsumed) {
        this.amountConsumed = amountConsumed;
    }
}

