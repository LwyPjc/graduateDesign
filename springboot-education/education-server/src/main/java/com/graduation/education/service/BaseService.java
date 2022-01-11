package com.graduation.education.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BaseService<T> extends IService<T> {
    default List<T> findList(T entity){
        Wrapper<T> wrapper = new QueryWrapper<>(entity);
        return this.list(wrapper);
    }
}
