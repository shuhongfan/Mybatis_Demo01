package com.shf.dao;

import com.shf.bean.Department;

public interface DepartmentMapper {
    public Department getDeptByIdStep(Integer Id);

    public Department getDeptByIdPlus(Integer Id);

    public Department getDeptById(Integer Id);

}
