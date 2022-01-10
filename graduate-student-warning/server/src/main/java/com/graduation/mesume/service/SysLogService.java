package com.graduation.mesume.service;

import com.graduation.mesume.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志  服务类
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
public interface SysLogService extends BaseService<SysLog> {
    /**
     * 统计访问数
     * @return
     */
    int countLoginCnt();

    /**
     * 统计当日访问数
     * @return
     */
    int countLoginTodayCnt();

    /**
     * 按月份统计登录数
     */
    List<Map<String,Integer>> countCntByMonth();
}
