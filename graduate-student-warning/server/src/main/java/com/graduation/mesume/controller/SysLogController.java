package com.graduation.mesume.controller;


import com.graduation.mesume.entity.SysLog;
import com.graduation.mesume.entity.SysUser;
import com.graduation.mesume.service.SysLogService;
import com.graduation.mesume.service.SysUserService;
import com.graduation.mesume.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志  前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Controller
@RequestMapping("/sysLog")
@ResponseBody
@CrossOrigin
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/login")
    public String login(String openid,String nickName){
        SysLog sysLog = new SysLog();
        sysLog.setOpId(openid);
        sysLog.setContent("登录博物馆系统");
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setType(Constants.OPERATE_TYPE_LOGIN);
        sysLogService.save(sysLog);
        //用户不存在时添加
        if(sysUserService.findByUserName(openid) ==null){
            SysUser user = new SysUser();
            user.setPassword(Constants.DEFAULT_PWD);
            user.setUsername(openid);
            user.setName(nickName);
            sysUserService.save(user);
        }
        return "success";
    }
}

