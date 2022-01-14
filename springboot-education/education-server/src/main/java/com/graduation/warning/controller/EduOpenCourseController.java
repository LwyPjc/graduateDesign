package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.*;
import com.graduation.warning.entity.dto.EduOpenCourseDto;
import com.graduation.warning.service.*;
import com.graduation.warning.util.Constant;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 开课信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/eduOpenCourse")
@CrossOrigin
public class EduOpenCourseController {
    @Autowired
    private EduOpenCourseService eduOpenCourseService;
    @Autowired
    private EduClassService eduClassService;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduClassroomService eduClassroomService;
    @Autowired
    private EduStudentService eduStudentService;
    @Autowired
    private EduStudentCourseService eduStudentCourseService;

    @GetMapping("/findList")
    public List<EduOpenCourse> findList(EduOpenCourse eduOpenCourse) {
        return eduOpenCourseService.findList(eduOpenCourse);
    }

    /**
     * 学生可以查看的开课信息，用于选课
     * @param eduOpenCourse
     * @param userName
     * @param page
     * @return
     */
    @GetMapping("/stuFindListByPage")
    public Page<EduOpenCourseDto> stuFindListByPage(EduOpenCourse eduOpenCourse,String userName, Page page) {
        //根据学号查找学生
        EduStudent student = eduStudentService.findByStuCode(userName);
        //根据学生id先过滤掉当前学生已经选过的课程，查询时排除掉这些课程
        EduStudentCourse filter = new EduStudentCourse();
        filter.setStudentId(student.getId());
        List<EduStudentCourse> studentCourses = eduStudentCourseService.findList(filter);
        QueryWrapper<EduOpenCourse> queryWrapper = new QueryWrapper<>(eduOpenCourse);
        //设置过滤条件 班级id
        queryWrapper.eq(Constant.CLASS_ID,student.getClassId());
        //设置过滤条件 开始选课时间大于当前时间，结束选择时间小于当前时间
        queryWrapper.lt(Constant.CHOOSE_START,new Date());
        queryWrapper.gt(Constant.CHOOSE_END,new Date());
        if(CollectionUtils.isNotEmpty(studentCourses)){
            //排除掉已经选择的课程
            queryWrapper.notIn(Constant.ID,studentCourses.stream().map(EduStudentCourse::getOpenCourseId).collect(Collectors.toSet()));
        }
        Page<EduOpenCourseDto> openCourseDtoPage = eduOpenCourseService.page(page, queryWrapper);
        List<EduOpenCourseDto> openCourseDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<openCourseDtoPage.getRecords().size();i++){
            EduOpenCourseDto trs = new EduOpenCourseDto();
            BeanUtil.copyProperties(openCourseDtoPage.getRecords().get(i),trs,true);
            //当选择人数已经满了，就删掉
            if(trs.getChooseNum()>=trs.getNumber()){
                continue;
            }
            openCourseDtos.add(trs);
        }
        if(CollectionUtils.isNotEmpty(openCourseDtos)){
            //将其中的班级id 教师id 教室id 课程id 转换成对应名称
            transferCourseInfo(openCourseDtos);
        }
        openCourseDtoPage.setRecords(openCourseDtos);
        return openCourseDtoPage;
    }

    @GetMapping("/findListByPage")
    public Page<EduOpenCourseDto> findListByPage(EduOpenCourse eduOpenCourse, Page page) {
        QueryWrapper<EduOpenCourse> queryWrapper = new QueryWrapper<>(eduOpenCourse);
        Page<EduOpenCourseDto> openCourseDtoPage = eduOpenCourseService.page(page, queryWrapper);
        List<EduOpenCourseDto> openCourseDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<openCourseDtoPage.getRecords().size();i++){
            EduOpenCourseDto trs = new EduOpenCourseDto();
            BeanUtil.copyProperties(openCourseDtoPage.getRecords().get(i),trs,true);
            openCourseDtos.add(trs);
        }
        //将其中的班级id 教师id 教室id 课程id 转换成对应名称
        transferCourseInfo(openCourseDtos);
        openCourseDtoPage.setRecords(openCourseDtos);
        return openCourseDtoPage;
    }

    @GetMapping("/{id}")
    public EduOpenCourse getById(@PathVariable String id) {
        return eduOpenCourseService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(EduOpenCourse eduOpenCourse) {
        eduOpenCourseService.save(eduOpenCourse);
        return eduOpenCourse.getId();
    }


    @PostMapping("/edit")
    public boolean edit(EduOpenCourse eduOpenCourse) {
        return eduOpenCourseService.updateById(eduOpenCourse);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return eduOpenCourseService.removeById(id);
    }

    /**
     * 将其中的班级id 教师id 教室id 课程id 转换成对应名称
     * @param dtos
     */
    private void transferCourseInfo(List<EduOpenCourseDto> dtos){
        //获取所有班级信息
        List<EduClass> classes = eduClassService.list();
        //获取所有课程
        List<EduCourse> courses = eduCourseService.list();
        //获取所有教室
        List<EduClassroom> classrooms = eduClassroomService.list();
        //获取所有教师
        List<EduTeacher> teachers = eduTeacherService.list();
        //获取所有教室
        //  班级id   班级信息
        Map<Integer,EduClass> idAndClass = classes.stream().collect(Collectors.toMap(x->x.getId(), x->x,(x1, x2)->x1));
        //  课程id    课程名称
        Map<Integer,String> courseIdAndName = courses.stream().collect(Collectors.toMap(EduCourse::getId,EduCourse::getName,(x1, x2)->x1));
        //  班级id   教师名
        Map<Integer,String> teacherIdAndName = teachers.stream().collect(Collectors.toMap(EduTeacher::getId,EduTeacher::getName,(x1,x2)->x1));
        //  教室id   教室名
        Map<Integer,String> roomIdAndName = classrooms.stream().collect(Collectors.toMap(EduClassroom::getId,EduClassroom::getName,(x1,x2)->x1));
        for(EduOpenCourseDto dto:dtos){
            if(idAndClass.containsKey(dto.getClassId())){
                EduClass eduClass = idAndClass.get(dto.getClassId());
                dto.setCollege(eduClass.getCollege());
                dto.setGrade(eduClass.getGrade());
                dto.setSubject(eduClass.getSubject());
                dto.setClassNo(eduClass.getClassNo());
                //将课程id转换成课程名称
                dto.setCourseName(courseIdAndName.getOrDefault(dto.getCourseId(),""));
                //将教师id转换教师名称
                dto.setTeacherName(teacherIdAndName.getOrDefault(dto.getTeacherId(),""));
                //将教室id转换教室名称
                dto.setRoomName(roomIdAndName.getOrDefault(dto.getClassroomId(),""));
            }
        }
    }
}

