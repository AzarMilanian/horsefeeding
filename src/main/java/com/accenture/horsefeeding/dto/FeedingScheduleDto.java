package com.accenture.horsefeeding.dto;

import java.util.ArrayList;
import java.util.List;

public class FeedingScheduleDto {
    private Long scheduleId;
    private Long horseId;
    private List<ScheduleRangeDto> ranges = new ArrayList<>();

    public FeedingScheduleDto() {
    }
    public FeedingScheduleDto(Long scheduleId, Long horseId) {
        this.scheduleId = scheduleId;
        this.horseId = horseId;
    }
    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    public Long getHorseId() {
        return horseId;
    }
    public void setHorseId(Long horseId) {
        this.horseId = horseId;
    }
    public List<ScheduleRangeDto> getRanges() {
        return ranges;
    }

}
