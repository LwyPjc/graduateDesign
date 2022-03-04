package com.hospital.appointment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.appointment.entity.Statistics;
import com.hospital.appointment.service.StatisticsHandlerService;
import com.hospital.appointment.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsHandlerServiceImpl implements StatisticsHandlerService {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 异步保存统计值
     */
    @Override
    @Async
    public void saveOrUpdate(String docName, Integer docId, String patientId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        // 查询医生的统计值
        QueryWrapper<Statistics> statisticsQueryWrapper = new QueryWrapper<>();
        statisticsQueryWrapper.eq("doctor_id", docId);
        statisticsQueryWrapper.eq("create_time", format);
        Statistics one = statisticsService.getOne(statisticsQueryWrapper);
        // 如果已有记录
        if (one != null) {
            String patientIds = one.getPatientIds();
            String[] split = patientIds.split(",");
            List<String> list = Arrays.asList(split);
            boolean yes = checkIfAlreadyExists(list, patientId);
            // 如果没存在过，加一
            if (!yes) {
                one.setValue(one.getValue() + 1);
                list.add(patientId);
                String join = String.join(",", list);
                one.setPatientIds(join);
                statisticsService.saveOrUpdate(one);
            }
        } else {
            // 这个医生今天第一次被咨询
            Statistics statistics = new Statistics();
            statistics.setCreateTime(new Date());
            statistics.setDoctorId(docId);
            statistics.setDoctorName(docName);
            statistics.setValue(1);
            statistics.setPatientIds(patientId);
            statisticsService.save(statistics);
        }
    }

    /**
     * @param ids
     * @param id
     * @return true 已存在 false不存在
     */
    private boolean checkIfAlreadyExists(List<String> ids, String id) {
        if (ids.contains(id)) {
            return true;
        }
        return false;
    }
}
