package com.graduation.warning.dao;

import com.graduation.warning.entity.EduStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
