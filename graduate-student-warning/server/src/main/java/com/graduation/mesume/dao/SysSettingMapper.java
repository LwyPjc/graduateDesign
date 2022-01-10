package com.graduation.mesume.dao;

import com.graduation.mesume.entity.SysSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统配置  Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Mapper
@Repository
public interface SysSettingMapper extends BaseMapper<SysSetting> {

}
