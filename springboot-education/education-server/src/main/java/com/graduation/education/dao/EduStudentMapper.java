package com.graduation.education.dao;

import com.graduation.education.entity.EduOpenCourse;
import com.graduation.education.entity.EduStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 学生信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Mapper
@Repository
public interface EduStudentMapper extends BaseMapper<EduStudent> {

}
