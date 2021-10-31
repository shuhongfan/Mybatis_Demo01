package com.shf.dao;

import com.shf.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);
    public void updateEmp2(Employee employee);

    public List<Employee> getEmpByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);
    public void addEmps2(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsTestInnerParameters(Employee employee);
}
