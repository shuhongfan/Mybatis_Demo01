package com.shf.mp.service.impl;

import com.shf.mp.beans.Employee;
import com.shf.mp.mapper.EmployeeMapper;
import com.shf.mp.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shf
 * @since 2021-11-01
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
//  不在进行mapper的注入
//    EmployeeServiceImpl   继承了ServiceImpl
}
