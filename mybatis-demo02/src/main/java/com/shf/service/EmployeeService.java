package com.shf.service;

import com.shf.bean.Employee;
import com.shf.dao.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeService {
    @Autowired
    EmpMapper empMapper;

    public List<Employee> employeeMapper;


}
