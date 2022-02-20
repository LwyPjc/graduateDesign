package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.appointment.entity.UserInfo;
import com.hospital.appointment.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static com.hospital.appointment.utils.CommUtils.checkIsNotNull;
import static com.hospital.appointment.utils.CommUtils.generateUUID;

/**
 * <p>标题: 服务 - 请求控制层</p>
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/findList")
    public List<UserInfo> findList(UserInfo userInfo) {
        return userInfoService.findList(userInfo);
    }

    @GetMapping("/findListByPage")
    public Page<UserInfo> findListByPage(UserInfo userInfo, Page page) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
        return userInfoService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public UserInfo getById(@PathVariable("id") String id) {
        UserInfo userInfo = userInfoService.getById(id);
        if ("1".equals(userInfo.getGender())){
            userInfo.setGender("女");
        }else if ("2".equals(userInfo.getGender())){
            userInfo.setGender("男");
        }
        return userInfo;
    }

    @PostMapping("/save")
    public Serializable save(@RequestBody UserInfo userInfo) {
        UserInfo userFromDb = userInfoService.getById(userInfo.getId());
        if (userFromDb != null) {
            return userInfo.getId();
        }
        userInfoService.save(userInfo);
        return userInfo.getId();
    }

    @PostMapping("/edit")
    public UserInfo edit(@RequestBody UserInfo userInfo) {
        if (checkIsNotNull(userInfo.getIdCard())) {
            //create medicine card
            String medicareCard = generateUUID().substring(0, 6);
            userInfo.setMedicareCard(medicareCard.toUpperCase());
        }
        userInfoService.updateById(userInfo);
        return userInfo;
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return userInfoService.removeById(id);
    }

    @GetMapping("/getByIdCard/{idCard}")
    public String checkIdCardExist(@PathVariable String idCard) {
        return userInfoService.getOpenIDByIdCard(idCard);
    }

}
