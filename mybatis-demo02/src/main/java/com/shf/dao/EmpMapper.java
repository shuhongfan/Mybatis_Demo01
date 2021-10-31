package com.shf.dao;

import com.shf.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
//    多条记录封装在map  键是这条记录的主键，值记录封装后的Javabean
    @MapKey("id") // 告诉mybatis封装这个map的时候使用哪个map作为key
    public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);

//    返回一条记录的Map key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmpByMap(Map<String,Object> map);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployeeById(int id);

    public Long addEmp(Employee employee);

    public Boolean updateEmp(Employee employee);

    public Boolean deleteEmp(Integer id);
}
