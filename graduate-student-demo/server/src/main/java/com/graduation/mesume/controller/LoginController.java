package com.graduation.mesume.controller;

import com.graduation.mesume.entity.SysUser;
import com.graduation.mesume.service.SysUserService;
import com.graduation.mesume.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制层
 */
@Controller
@RequestMapping("/login")
@CrossOrigin
@ResponseBody
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 检验用户名密码是否正确
     */
    @PostMapping("/loginCheck")
    public ResultMap getById(String username, String password) {
        ResultMap resultMap = new ResultMap();
        SysUser user = sysUserService.findByUserName(username);
        if (user == null) {
            resultMap.setError("用户名不存在");
        }else if (!user.getPassword().equals(password)){
            resultMap.setError("密码错误");
        }else {
            resultMap.setSuccss(user);
        }
        return resultMap;
    }


}
