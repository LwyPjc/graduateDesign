package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.Class;
import com.graduation.warning.entity.Student;
import com.graduation.warning.entity.Teacher;
import com.graduation.warning.entity.SysUser;
import com.graduation.warning.entity.dto.StudentDto;
import com.graduation.warning.service.ClassService;
import com.graduation.warning.service.StudentService;
import com.graduation.warning.service.TeacherService;
import com.graduation.warning.service.SysUserService;
import com.graduation.warning.util.Constant;
import com.graduation.warning.util.ResultMap;
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
 * @since 2022-02-19
 */
@Controller
@ResponseBody
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findList")
    public List<Student> findList(Student student) {
        return studentService.findList(student);
    }

    @GetMapping("/findListByPage")
    public Page<StudentDto> findListByPage(Student student, Page page) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>(student);
        Page<StudentDto> studentPage = studentService.page(page, queryWrapper);
        List<StudentDto> studentDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<studentPage.getRecords().size();i++){
            StudentDto trs = new StudentDto();
            BeanUtil.copyProperties(studentPage.getRecords().get(i),trs,true);
            studentDtos.add(trs);
        }
        //将其中的班级id转换成班级的消息信息（学院 年级 专业）
        transferClassInfo(studentDtos);
        studentPage.setRecords(studentDtos);
        return studentPage;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @PostMapping("/save")
    public ResultMap save(Student student) {
        ResultMap map = new ResultMap();
        if(studentService.findByStuCode(student.getStuCode())!=null){
            map.setError("学号已存在，请重新输入");
            return map;
        }
        studentService.save(student);
        //新增学生信息之后也要新增用户信息，这样学生才能登录
        SysUser user = new SysUser();
        //学号就是用户名
        user.setUsername(student.getStuCode());
        user.setPassword(Constant.DEFAULT_PASSWORD);
        user.setRole("1");
        sysUserService.save(user);
        map.setSuccss("操作成功");
        return map;
    }

    @PostMapping("/edit")
    public ResultMap edit(StudentDto eduStudent) {
        ResultMap map = new ResultMap();
        studentService.updateById(eduStudent);
        map.setSuccss("操作成功");
        return map;
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        //删除学生信息的同时也要删除用户信息
        Student student = studentService.getById(id);
        //根据学号删除用户信息
        sysUserService.removeByUserName(student.getStuCode());
        return studentService.removeById(id);
    }

    /**
     * 将班级id转换成班级信息
     * @param dtos
     */
    private void transferClassInfo(List<StudentDto> dtos){
        //获取所有班级信息
        List<Class> classes = classService.list();
        //获取所有教师
        List<Teacher> teachers = teacherService.list();
        //  班级id   班级信息
        Map<Integer, Class> idAndClass = classes.stream().collect(Collectors.toMap(x->x.getId(), x->x,(x1, x2)->x1));
        //  班级id   教师名
        Map<Integer,String> idAndName = teachers.stream().collect(Collectors.toMap(Teacher::getId, Teacher::getName,(x1, x2)->x1));

        for(StudentDto dto:dtos){
            if(idAndClass.containsKey(dto.getClassId())){
                Class aClass = idAndClass.get(dto.getClassId());
                dto.setCollege(aClass.getCollege());
                dto.setGrade(aClass.getGrade());
                dto.setSubject(aClass.getSubject());
                dto.setClassNo(aClass.getClassNo());
                dto.setCounselorName(idAndName.getOrDefault(aClass.getCounselorId(),""));
            }
        }
    }

}

