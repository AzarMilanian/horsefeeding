package com.accenture.horsefeeding.dto;

import java.time.LocalTime;

public class ScheduleRangeDto {
    private Long rangeId;
    private Long scheduleId;
    private LocalTime feedingRangeStart;
    private LocalTime feedingRangeEnd;

    public Long getRangeId() {
        return rangeId;
    }

    public void setRangeId(Long rangeId) {
        this.rangeId = rangeId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalTime getFeedingRangeStart() {
        return feedingRangeStart;
    }

    public void setFeedingRangeStart(LocalTime feedingRangeStart) {
        this.feedingRangeStart = feedingRangeStart;
    }

    public LocalTime getFeedingRangeEnd() {
        return feedingRangeEnd;
    }

    public void setFeedingRangeEnd(LocalTime feedingRangeEnd) {
        this.feedingRangeEnd = feedingRangeEnd;
    }
}

