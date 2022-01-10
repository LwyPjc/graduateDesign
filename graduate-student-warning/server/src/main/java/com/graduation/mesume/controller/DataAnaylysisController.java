package com.graduation.mesume.controller;

import com.google.common.collect.Maps;
import com.graduation.mesume.entity.SysLog;
import com.graduation.mesume.entity.ZtAdvice;
import com.graduation.mesume.service.SysLogService;
import com.graduation.mesume.service.ZtAdviceService;
import com.graduation.mesume.service.ZtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 操作日志  前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Controller
@RequestMapping("/data")
@ResponseBody
@CrossOrigin
public class DataAnaylysisController {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private ZtGoodsService ztGoodsService;
    @Autowired
    private ZtAdviceService ztAdviceService;

    /**
     * 数据总览
     * @return
     */
    @GetMapping("/total")
    public Map<String,Object> total() {
        Map<String,Object> result = Maps.newHashMap();
        result.put("todayLoginCnt",sysLogService.countLoginTodayCnt());
        result.put("loginCnt",sysLogService.countLoginCnt());
        result.put("goodsCnt",ztGoodsService.count());
        result.put("adviceCnt",ztAdviceService.count());
        result.put("monthsCnt",sysLogService.countCntByMonth());
        return result;
    }

}
