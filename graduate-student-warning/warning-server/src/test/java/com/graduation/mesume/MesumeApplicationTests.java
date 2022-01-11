package com.graduation.mesume;

import com.google.common.collect.Maps;
import com.graduation.mesume.controller.DataAnaylysisController;
import com.graduation.mesume.dao.SysUserMapper;
import com.graduation.mesume.service.SysLogService;
import com.graduation.mesume.service.ZtAdviceService;
import com.graduation.mesume.service.ZtGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class MesumeApplicationTests {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private ZtGoodsService ztGoodsService;
    @Autowired
    private ZtAdviceService ztAdviceService;

    @Test
    void contextLoads() {
        Map<String,Object> result = Maps.newHashMap();
        result.put("todayLoginCnt",sysLogService.countLoginTodayCnt());
        result.put("oginCnt",sysLogService.countLoginCnt());
        result.put("goodsCnt",ztGoodsService.count());
        result.put("adviceCnt",ztAdviceService.count());
        result.put("monthsCnt",sysLogService.countCntByMonth());
        System.out.println(result.toString());
    }

}
