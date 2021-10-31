package com.shf.dao;

import com.shf.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmpMapperAnnotation {
    @Select("select * from tbl_employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
