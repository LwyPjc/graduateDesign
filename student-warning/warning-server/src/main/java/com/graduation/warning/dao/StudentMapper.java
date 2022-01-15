package com.graduation.warning.dao;

import com.graduation.warning.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

}
