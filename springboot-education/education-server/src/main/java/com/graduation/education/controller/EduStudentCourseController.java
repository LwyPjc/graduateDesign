package com.graduation.education.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.google.common.collect.Lists;
import com.graduation.education.entity.EduStudent;
import com.graduation.education.entity.EduStudentCourse;
import com.graduation.education.entity.dto.EduOpenCourseDto;
import com.graduation.education.service.EduOpenCourseService;
import com.graduation.education.service.EduStudentCourseService;
import com.graduation.education.service.EduStudentService;
import com.graduation.education.util.ResultMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 选课信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Controller
@ResponseBody
@RequestMapping("/eduStudentCourse")
@CrossOrigin
public class EduStudentCourseController {
    @Autowired
    private EduStudentCourseService eduStudentCourseService;
    @Autowired
    private EduOpenCourseService eduOpenCourseService;
    @Autowired
    private EduStudentService eduStudentService;

    @GetMapping("/findList")
    public List<EduStudentCourse> findList(EduStudentCourse eduStudentCourse) {
        return eduStudentCourseService.findList(eduStudentCourse);
    }

    @GetMapping("/findListByPage")
    public Page<EduStudentCourse> findListByPage(EduStudentCourse eduStudentCourse, Page page) {
        QueryWrapper<EduStudentCourse> queryWrapper = new QueryWrapper<>(eduStudentCourse);
        return eduStudentCourseService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public EduStudentCourse getById(@PathVariable String id) {
        return eduStudentCourseService.getById(id);
    }

    @PostMapping("/save")
    @Transactional
    public ResultMap save(EduStudentCourse eduStudentCourse, String userName) {
        ResultMap resultMap = new ResultMap();
        if(StringUtils.isNotEmpty(userName)){
            EduStudent student = eduStudentService.findByStuCode(userName);
            eduStudentCourse.setStudentId(student.getId());
            //如果课程冲突，则直接返回错误信息
            if(eduStudentCourseService.isCourseConflict(student.getId(),eduStudentCourse.getOpenCourseId())){
                resultMap.setError("所选课程的上课时间与已有课程冲突，请重新选择");
                return resultMap;
            }
            //将开课信息表中的选课人数+1
            eduOpenCourseService.chooseCourse(eduStudentCourse.getOpenCourseId());
        }
        eduStudentCourseService.save(eduStudentCourse);
        resultMap.setSuccss("选课成功");
        return resultMap;
    }

    @PostMapping("/edit")
    public boolean edit(EduStudentCourse eduStudentCourse) {
        return eduStudentCourseService.updateById(eduStudentCourse);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduStudentCourseService.removeById(id);
    }

    /**
     * 查询当前登录用户的课程表
     */
    @GetMapping("/timetable")
    public String timetable(String userName){
        //根据学号查出学生用户id
        EduStudent student = eduStudentService.findByStuCode(userName);
        //查询出当前学生所有的课程
        List<EduOpenCourseDto> courseDtos = eduOpenCourseService.findStudentCourse(student.getId());
        //一周五天 一天十节课，构建一个二维数组
        String[][] timetable = new String[5][10];
        //初始化数组
        for(int i = 0;i<5;i++){
            for(int j = 0;j<10;j++){
                timetable[i][j] = "";
            }
        }
        for(EduOpenCourseDto courseDto:courseDtos){
            String courseInfo = courseDto.getWeekStart() + "-" + courseDto.getWeekEnd() + "\r\n" + courseDto.getCourseName() + "\r\n"
                    +courseDto.getRoomName() + "\r\n" +  courseDto.getTeacherName();
            //对每个节次都设置一条课程信息
            for(int i = courseDto.getNodeStart();i<= courseDto.getNodeEnd(); i++){
                //对每个周次都设置一条课程信息
                List<String> weekTime = Lists.newArrayList(courseDto.getWeekTime().split(Constants.COMMA));
                for(String time:weekTime){
                    timetable[Integer.valueOf(time)-1][i-1] = courseInfo;
                }
            }
        }
        return JSONUtil.toJsonStr(timetable);
    }

}

