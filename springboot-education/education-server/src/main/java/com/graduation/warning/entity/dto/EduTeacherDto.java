package com.graduation.warning.entity.dto;

import com.graduation.warning.entity.EduTeacher;

import java.util.List;

/**
 * @author shizhuohuan
 * @date 2021/12/3 15:08
 */
public class EduTeacherDto extends EduTeacher {
    /**
     * 关联班级id集合
     */
    private List<Integer> relateClassIdList;

    /**
     * 关联班级名称
     */
    private String relateClassName;

    public List<Integer> getRelateClassIdList() {
        return relateClassIdList;
    }

    public void setRelateClassIdList(List<Integer> relateClassIdList) {
        this.relateClassIdList = relateClassIdList;
    }

    public String getRelateClassName() {
        return relateClassName;
    }

    public void setRelateClassName(String relateClassName) {
        this.relateClassName = relateClassName;
    }
}
