package com.shf.dao;

import com.shf.bean.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id) ;

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
