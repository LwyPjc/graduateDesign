package com.graduation.warning.dao;

import com.graduation.warning.entity.EduOpenCourse;
import com.graduation.warning.entity.EduStudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 选课信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Mapper
@Repository
public interface EduStudentCourseMapper extends BaseMapper<EduStudentCourse> {
    /**
     * 根据条件查询学生选课信息
     */
    List<EduOpenCourse> getCourseByCondition(String term, int nodeStart, int nodeEnd, int studentId,int weekStart,int weekEnd);
}
