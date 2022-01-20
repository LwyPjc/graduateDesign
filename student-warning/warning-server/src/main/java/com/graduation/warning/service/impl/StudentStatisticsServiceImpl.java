package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.warning.entity.*;
import com.graduation.warning.service.WarningRuleService;
import com.graduation.warning.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.dao.StudentStatisticsMapper;
import com.graduation.warning.service.StudentStatisticsService;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-1-20
 */
@Service
public class StudentStatisticsServiceImpl extends ServiceImpl<StudentStatisticsMapper, StudentStatistics> implements StudentStatisticsService {

    @Autowired
    private WarningRuleService warningRuleService;

    @Override
    public void updateOrSave(StudentStatistics studentStatistics) {
        QueryWrapper<StudentStatistics> queryWrapper = new QueryWrapper<>(studentStatistics);
        queryWrapper.eq(Constant.TEACHER_NAME, studentStatistics.getTeacherName());
        queryWrapper.eq(Constant.STU_NAME, studentStatistics.getStudentName());
        queryWrapper.eq("type", studentStatistics.getType());
        queryWrapper.eq("warning_level", studentStatistics.getWarningLevel());
//        queryWrapper.eq("value", studentStatistics.getValue());
        StudentStatistics one = this.getOne(queryWrapper);
        if (one == null) {
            this.save(studentStatistics);
        }
    }

    @Override
    public void handleScore(ScoreEvaluate scoreEvaluate) {
        String type = "3";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }
        BigDecimal score = scoreEvaluate.getScore();

        for (WarningRule warningRule : warningRules) {
            String value = warningRule.getValue();
            BigDecimal bigDecimal = new BigDecimal(value);
            if (score.compareTo(bigDecimal) < 0) {
                StudentStatistics studentStatistics = new StudentStatistics();
                studentStatistics.setStudentName(scoreEvaluate.getStudentName());
                studentStatistics.setCourseName(scoreEvaluate.getCourseName());
                studentStatistics.setTeacherName(scoreEvaluate.getTeacherName());
                studentStatistics.setType(type);
                studentStatistics.setWarningLevel(warningRule.getWarningLevel());
                updateOrSave(studentStatistics);
                return;
            }
        }
    }


    @Override
    public void handleParticipation(ParticipationEvaluate participationEvaluate) {
        String type = "1";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }
        Integer count = participationEvaluate.getCount();
        int countValue = 0;
        WarningRule warningRuleResult = null;
        for (WarningRule warningRule : warningRules) {
            String value = warningRule.getValue();
            Integer warningCnt = Integer.valueOf(value);
            if (count >= warningCnt) {
                if (countValue > warningCnt) {
                    continue;
                }
                countValue = warningCnt;
                warningRuleResult = warningRule;
            }
        }
        if (warningRuleResult != null) {
            StudentStatistics studentStatistics = new StudentStatistics();
            studentStatistics.setStudentName(participationEvaluate.getStudentName());
            studentStatistics.setCourseName(participationEvaluate.getCourseName());
            studentStatistics.setTeacherName(participationEvaluate.getTeacherName());
            studentStatistics.setType(type);
            studentStatistics.setWarningLevel(warningRuleResult.getWarningLevel());
            updateOrSave(studentStatistics);
        }
    }

    @Override
    public void handleTask(TaskEvaluate taskEvaluate) {
        String type = "2";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }

        Integer count = taskEvaluate.getCount();
        int countValue = 0;
        WarningRule warningRuleResult = null;
        for (WarningRule warningRule : warningRules) {
            String value = warningRule.getValue();
            Integer warningCnt = Integer.valueOf(value);
            if (count >= warningCnt) {
                if (countValue > warningCnt) {
                    continue;
                }
                countValue = warningCnt;
                warningRuleResult = warningRule;
            }
        }
        if (warningRuleResult != null) {
            StudentStatistics studentStatistics = new StudentStatistics();
            studentStatistics.setStudentName(taskEvaluate.getStudentName());
            studentStatistics.setCourseName(taskEvaluate.getCourseName());
            studentStatistics.setTeacherName(taskEvaluate.getTeacherName());
            studentStatistics.setType(type);
            studentStatistics.setWarningLevel(warningRuleResult.getWarningLevel());
            updateOrSave(studentStatistics);
        }
    }

    private Map<String, List<WarningRule>> getWarningRuleMap() {
        List<WarningRule> list = warningRuleService.findList(new WarningRule());
        Map<String, List<WarningRule>> collect = list.stream().collect(Collectors.groupingBy(WarningRule::getType));
        return collect;
    }
}
