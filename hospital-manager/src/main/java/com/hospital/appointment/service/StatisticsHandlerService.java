package com.hospital.appointment.service;

import java.util.Date;

/**
 * 统计处理类
 */
public interface StatisticsHandlerService {

    /**
     * 给某个医生添加统计值
     */
    void saveOrUpdate(String docName,Integer docId, String patientId);
}
