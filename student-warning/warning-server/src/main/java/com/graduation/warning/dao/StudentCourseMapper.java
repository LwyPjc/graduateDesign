package com.graduation.warning.dao;

import com.graduation.warning.entity.OpenCourse;
import com.graduation.warning.entity.StudentCourse;
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
 * @since 2022-02-19
 */
@Mapper
@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {
    /**
     * 根据条件查询学生选课信息
     */
    List<OpenCourse> getCourseByCondition(String term, int nodeStart, int nodeEnd, int studentId, int weekStart, int weekEnd);
}
