package com.graduation.mesume.dao;

import com.graduation.mesume.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志  Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 统计访问数
     * @return
     */
    int countCnt(int type);

    /**
     * 统计当日访问数
     * @return
     */
    int countTodayCnt(int type,String time);

    /**
     * 按月份统计登录数
     */
    List<Map<String,Integer>> countCntByMonth(int type);

}
