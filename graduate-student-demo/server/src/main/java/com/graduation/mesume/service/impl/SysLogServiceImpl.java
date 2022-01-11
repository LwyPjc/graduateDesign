package com.graduation.mesume.service.impl;

import cn.hutool.core.date.DateUtil;
import com.graduation.mesume.entity.SysLog;
import com.graduation.mesume.dao.SysLogMapper;
import com.graduation.mesume.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.mesume.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志  服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int countLoginCnt() {
        return sysLogMapper.countCnt(Constants.OPERATE_TYPE_LOGIN);
    }

    @Override
    public int countLoginTodayCnt() {
        Date now = new Date();
        String nowDate = DateUtil.format(now,"yyyy-MM-dd");
        nowDate += "  00:00:00";
        return sysLogMapper.countTodayCnt(Constants.OPERATE_TYPE_LOGIN,nowDate);
    }

    @Override
    public List<Map<String, Integer>> countCntByMonth() {
        return sysLogMapper.countCntByMonth(Constants.OPERATE_TYPE_LOGIN);
    }
}
