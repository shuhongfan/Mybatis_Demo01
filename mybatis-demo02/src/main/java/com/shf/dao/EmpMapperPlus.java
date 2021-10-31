package com.shf.dao;

import com.shf.bean.Employee;

import java.util.List;

public interface EmpMapperPlus {
    public List<Employee> getEmpsById(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpById(Integer id);

}
