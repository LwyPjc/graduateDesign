package com.graduation.warning.controller;

import com.graduation.warning.entity.SysUser;
import com.graduation.warning.service.SysUserService;
import com.graduation.warning.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
