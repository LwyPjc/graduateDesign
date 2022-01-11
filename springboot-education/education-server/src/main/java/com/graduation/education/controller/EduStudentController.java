package com.graduation.education.controller;


import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.graduation.education.entity.EduClass;
import com.graduation.education.entity.EduStudent;
import com.graduation.education.entity.EduTeacher;
import com.graduation.education.entity.SysUser;
import com.graduation.education.entity.dto.EduStudentDto;
import com.graduation.education.service.EduClassService;
import com.graduation.education.service.EduStudentService;
import com.graduation.education.service.EduTeacherService;
import com.graduation.education.service.SysUserService;
import com.graduation.education.util.Constant;
import com.graduation.education.util.ResultMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Controller
@ResponseBody
@RequestMapping("/eduStudent")
@CrossOrigin
public class EduStudentController {
    @Autowired
    private EduStudentService eduStudentService;
    @Autowired
    private EduClassService eduClassService;
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findList")
    public List<EduStudent> findList(EduStudent eduStudent) {
        return eduStudentService.findList(eduStudent);
    }

    @GetMapping("/findListByPage")
    public Page<EduStudentDto> findListByPage(EduStudent eduStudent, Page page) {
        QueryWrapper<EduStudent> queryWrapper = new QueryWrapper<>(eduStudent);
        Page<EduStudentDto> studentPage = eduStudentService.page(page, queryWrapper);
        List<EduStudentDto> studentDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<studentPage.getRecords().size();i++){
            EduStudentDto trs = new EduStudentDto();
            BeanUtil.copyProperties(studentPage.getRecords().get(i),trs,true);
            studentDtos.add(trs);
        }
        //将其中的班级id转换成班级的消息信息（学院 年级 专业）
        transferClassInfo(studentDtos);
        studentPage.setRecords(studentDtos);
        return studentPage;
    }

    @GetMapping("/{id}")
    public EduStudent getById(@PathVariable String id) {
        return eduStudentService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(EduStudent eduStudent) {
        ResultMap map = new ResultMap();
        if(eduStudentService.findByStuCode(eduStudent.getStuCode())!=null){
            map.setError("学号已存在，请重新输入");
            return map;
        }
        eduStudentService.save(eduStudent);
        //新增学生信息之后也要新增用户信息，这样学生才能登录
        SysUser user = new SysUser();
        //学号就是用户名
        user.setUsername(eduStudent.getStuCode());
        user.setPassword(Constant.DEFAULT_PASSWORD);
        user.setRole("1");
        sysUserService.save(user);
        map.setSuccss("操作成功");
        return map;
    }

    @PostMapping("/edit")
    public ResultMap edit(EduStudentDto eduStudent) {
        ResultMap map = new ResultMap();
        eduStudentService.updateById(eduStudent);
        map.setSuccss("操作成功");
        return map;
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        //删除学生信息的同时也要删除用户信息
        EduStudent eduStudent = eduStudentService.getById(id);
        //根据学号删除用户信息
        sysUserService.removeByUserName(eduStudent.getStuCode());
        return eduStudentService.removeById(id);
    }

    /**
     * 将班级id转换成班级信息
     * @param dtos
     */
    private void transferClassInfo(List<EduStudentDto> dtos){
        //获取所有班级信息
        List<EduClass> classes = eduClassService.list();
        //获取所有教师
        List<EduTeacher> teachers = eduTeacherService.list();
        //  班级id   班级信息
        Map<Integer,EduClass> idAndClass = classes.stream().collect(Collectors.toMap(x->x.getId(),x->x,(x1,x2)->x1));
        //  班级id   教师名
        Map<Integer,String> idAndName = teachers.stream().collect(Collectors.toMap(EduTeacher::getId,EduTeacher::getName,(x1,x2)->x1));

        for(EduStudentDto dto:dtos){
            if(idAndClass.containsKey(dto.getClassId())){
                EduClass eduClass = idAndClass.get(dto.getClassId());
                dto.setCollege(eduClass.getCollege());
                dto.setGrade(eduClass.getGrade());
                dto.setSubject(eduClass.getSubject());
                dto.setClassNo(eduClass.getClassNo());
                dto.setCounselorName(idAndName.getOrDefault(eduClass.getCounselorId(),""));
            }
        }
    }

}

