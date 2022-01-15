package com.graduation.warning.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.graduation.warning.entity.*;
import com.graduation.warning.entity.Class;
import com.graduation.warning.entity.dto.OpenCourseDto;
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
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/eduOpenCourse")
@CrossOrigin
public class OpenCourseController {
    @Autowired
    private OpenCourseService openCourseService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/findList")
    public List<OpenCourse> findList(OpenCourse openCourse) {
        return openCourseService.findList(openCourse);
    }

    /**
     * 学生可以查看的开课信息，用于选课
     * @param openCourse
     * @param userName
     * @param page
     * @return
     */
    @GetMapping("/stuFindListByPage")
    public Page<OpenCourseDto> stuFindListByPage(OpenCourse openCourse, String userName, Page page) {
        //根据学号查找学生
        Student student = studentService.findByStuCode(userName);
        //根据学生id先过滤掉当前学生已经选过的课程，查询时排除掉这些课程
        StudentCourse filter = new StudentCourse();
        filter.setStudentId(student.getId());
        List<StudentCourse> studentCourses = studentCourseService.findList(filter);
        QueryWrapper<OpenCourse> queryWrapper = new QueryWrapper<>(openCourse);
        //设置过滤条件 班级id
        queryWrapper.eq(Constant.CLASS_ID,student.getClassId());
        //设置过滤条件 开始选课时间大于当前时间，结束选择时间小于当前时间
        queryWrapper.lt(Constant.CHOOSE_START,new Date());
        queryWrapper.gt(Constant.CHOOSE_END,new Date());
        if(CollectionUtils.isNotEmpty(studentCourses)){
            //排除掉已经选择的课程
            queryWrapper.notIn(Constant.ID,studentCourses.stream().map(StudentCourse::getOpenCourseId).collect(Collectors.toSet()));
        }
        Page<OpenCourseDto> openCourseDtoPage = openCourseService.page(page, queryWrapper);
        List<OpenCourseDto> openCourseDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<openCourseDtoPage.getRecords().size();i++){
            OpenCourseDto trs = new OpenCourseDto();
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
    public Page<OpenCourseDto> findListByPage(OpenCourse openCourse, Page page) {
        QueryWrapper<OpenCourse> queryWrapper = new QueryWrapper<>(openCourse);
        Page<OpenCourseDto> openCourseDtoPage = openCourseService.page(page, queryWrapper);
        List<OpenCourseDto> openCourseDtos = Lists.newArrayList();
        //将分页中的数据都取出来，将其中的班级id转换成班级的消息信息（学院 年级 专业）
        for(int i = 0;i<openCourseDtoPage.getRecords().size();i++){
            OpenCourseDto trs = new OpenCourseDto();
            BeanUtil.copyProperties(openCourseDtoPage.getRecords().get(i),trs,true);
            openCourseDtos.add(trs);
        }
        //将其中的班级id 教师id 教室id 课程id 转换成对应名称
        transferCourseInfo(openCourseDtos);
        openCourseDtoPage.setRecords(openCourseDtos);
        return openCourseDtoPage;
    }

    @GetMapping("/{id}")
    public OpenCourse getById(@PathVariable String id) {
        return openCourseService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(OpenCourse openCourse) {
        openCourseService.save(openCourse);
        return openCourse.getId();
    }


    @PostMapping("/edit")
    public boolean edit(OpenCourse openCourse) {
        return openCourseService.updateById(openCourse);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return openCourseService.removeById(id);
    }

    /**
     * 将其中的班级id 教师id 教室id 课程id 转换成对应名称
     * @param dtos
     */
    private void transferCourseInfo(List<OpenCourseDto> dtos){
        //获取所有班级信息
        List<Class> classes = classService.list();
        //获取所有课程
        List<Course> courses = courseService.list();
        //获取所有教室
        List<Classroom> classrooms = classroomService.list();
        //获取所有教师
        List<Teacher> teachers = teacherService.list();
        //获取所有教室
        //  班级id   班级信息
        Map<Integer, Class> idAndClass = classes.stream().collect(Collectors.toMap(x->x.getId(), x->x,(x1, x2)->x1));
        //  课程id    课程名称
        Map<Integer,String> courseIdAndName = courses.stream().collect(Collectors.toMap(Course::getId, Course::getName,(x1, x2)->x1));
        //  班级id   教师名
        Map<Integer,String> teacherIdAndName = teachers.stream().collect(Collectors.toMap(Teacher::getId, Teacher::getName,(x1, x2)->x1));
        //  教室id   教室名
        Map<Integer,String> roomIdAndName = classrooms.stream().collect(Collectors.toMap(Classroom::getId, Classroom::getName,(x1, x2)->x1));
        for(OpenCourseDto dto:dtos){
            if(idAndClass.containsKey(dto.getClassId())){
                Class aClass = idAndClass.get(dto.getClassId());
                dto.setCollege(aClass.getCollege());
                dto.setGrade(aClass.getGrade());
                dto.setSubject(aClass.getSubject());
                dto.setClassNo(aClass.getClassNo());
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

