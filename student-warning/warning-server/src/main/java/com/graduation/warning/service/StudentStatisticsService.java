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
    void updateOrSave(StudentStatistics studentStatistics);

    void handleScore(ScoreEvaluate scoreEvaluate);

    void handleParticipation(ParticipationEvaluate participationEvaluate);

    void handleTask(TaskEvaluate taskEvaluate);
}