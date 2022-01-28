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
    public void saveStatistic(StudentStatistics studentStatistics) {
        // 先到数据库中查询，此学生的统计情况，若已存在则不保存
        QueryWrapper<StudentStatistics> queryWrapper = new QueryWrapper<>(studentStatistics);
        queryWrapper.eq(Constant.TEACHER_NAME, studentStatistics.getTeacherName());
        queryWrapper.eq(Constant.STU_NAME, studentStatistics.getStudentName());
        queryWrapper.eq("type", studentStatistics.getType());
        queryWrapper.eq("warning_level", studentStatistics.getWarningLevel());
        StudentStatistics one = this.getOne(queryWrapper);
        if (one == null) {
            // 不存在才保存
            this.save(studentStatistics);
        }
    }

    @Override
    public void handleScore(ScoreEvaluate scoreEvaluate) {
        // 成绩类型
        String type = "3";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }
        BigDecimal score = scoreEvaluate.getScore();
        // 判读成绩是否符合预警设置的值
        for (WarningRule warningRule : warningRules) {
            String value = warningRule.getValue();
            BigDecimal bigDecimal = new BigDecimal(value);
            if (score.compareTo(bigDecimal) < 0) {
                StudentStatistics studentStatistics = new StudentStatistics();
                studentStatistics.setStudentName(scoreEvaluate.getStudentName());
                studentStatistics.setCourseName(scoreEvaluate.getCourseName());
                studentStatistics.setTeacherName(scoreEvaluate.getTeacherName());
                studentStatistics.setStudentId(scoreEvaluate.getStudentId());
                studentStatistics.setType(type);
                studentStatistics.setWarningLevel(warningRule.getWarningLevel());
                // 调用插入方法
                saveStatistic(studentStatistics);
                return;
            }
        }
    }


    @Override
    public void handleParticipation(ParticipationEvaluate participationEvaluate) {
        // 缺勤类型
        String type = "1";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }
        Integer count = participationEvaluate.getCount();
        int countValue = 0;
        WarningRule warningRuleResult = null;
        // 判断是否符合预警值
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
        // 符合预警值则保存
        if (warningRuleResult != null) {
            StudentStatistics studentStatistics = new StudentStatistics();
            studentStatistics.setStudentName(participationEvaluate.getStudentName());
            studentStatistics.setCourseName(participationEvaluate.getCourseName());
            studentStatistics.setTeacherName(participationEvaluate.getTeacherName());
            studentStatistics.setType(type);
            studentStatistics.setWarningLevel(warningRuleResult.getWarningLevel());
            studentStatistics.setStudentId(participationEvaluate.getStudentId());
            saveStatistic(studentStatistics);
        }
    }

    @Override
    public void handleTask(TaskEvaluate taskEvaluate) {
        // 作业类型
        String type = "2";
        Map<String, List<WarningRule>> warningRuleMap = getWarningRuleMap();
        List<WarningRule> warningRules = warningRuleMap.get(type);
        if (CollectionUtils.isEmpty(warningRules)) {
            return;
        }

        Integer count = taskEvaluate.getCount();
        int countValue = 0;
        WarningRule warningRuleResult = null;
        // 判断是否符合预警值
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
        // 符合的话则保存
        if (warningRuleResult != null) {
            StudentStatistics studentStatistics = new StudentStatistics();
            studentStatistics.setStudentName(taskEvaluate.getStudentName());
            studentStatistics.setCourseName(taskEvaluate.getCourseName());
            studentStatistics.setTeacherName(taskEvaluate.getTeacherName());
            studentStatistics.setType(type);
            studentStatistics.setStudentId(taskEvaluate.getStudentId());
            studentStatistics.setWarningLevel(warningRuleResult.getWarningLevel());
            saveStatistic(studentStatistics);
        }
    }

    // 查询管理员预设的预警等级转为map
    private Map<String, List<WarningRule>> getWarningRuleMap() {
        List<WarningRule> list = warningRuleService.findList(new WarningRule());
        Map<String, List<WarningRule>> collect = list.stream().collect(Collectors.groupingBy(WarningRule::getType));
        return collect;
    }
}
