package com.shf.dao;

import com.shf.bean.Employee;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public boolean addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public boolean daleteEmpById(Integer id);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastname);

    public Employee getEmpByMap(Map<String, Object> map);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    @MapKey("id")
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);
}
