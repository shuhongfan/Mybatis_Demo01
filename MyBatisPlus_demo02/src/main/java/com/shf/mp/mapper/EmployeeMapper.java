package com.shf.mp.mapper;

import com.shf.mp.beans.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shf
 * @since 2021-11-01
 */

//在Mapper接口中自定义相关的CRDU方法
public interface EmployeeMapper extends BaseMapper<Employee> {
    int deleteAll();
}
