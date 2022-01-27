package com.graduation.warning.service;

import com.graduation.warning.entity.*;

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
public interface StudentStatisticsService extends BaseService<StudentStatistics> {
    /**
     * 判断是更新还是插入学生统计情况
     * @param studentStatistics
     */
    void updateOrSave(StudentStatistics studentStatistics);

    /**
     * 处理分数成绩
     * @param scoreEvaluate
     */
    void handleScore(ScoreEvaluate scoreEvaluate);

    /**
     * 处理考勤情况
     * @param participationEvaluate
     */
    void handleParticipation(ParticipationEvaluate participationEvaluate);

    /**
     * 处理作业情况
     * @param taskEvaluate
     */
    void handleTask(TaskEvaluate taskEvaluate);
}