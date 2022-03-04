package com.hospital.appointment.entity.dto;

import com.hospital.appointment.entity.FeedbackInfo;
import com.hospital.appointment.entity.Statistics;

import java.util.Date;

public class StatisticsDto extends Statistics {
    private Long dateStart;
    private Long dateEnd;

    public Date getDateStartWithDate(){
        return new Date(dateStart);
    }
    public Date getDateEndWithDate(){
        return new Date(dateEnd);
    }

    public Long getDateStart() {
        return dateStart;
    }

    public void setDateStart(Long dateStart) {
        this.dateStart = dateStart;
    }

    public Long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Long dateEnd) {
        this.dateEnd = dateEnd;
    }
}
