package com.shf.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shf.beans.Employee;

//Mapper接口
//基于Mybatis：在Mapper接口中编写CRUD相关的方法 提供Mapper接口所对应的SQL映射文件 以及方法对应的SQL语句
//基于MP：让XXXMapper接口继承BAseMapper即可
//    BaseMapper<T>: 泛型指定的就是当前Mapper接口所操作的实体类类型
public interface EmployeeMapper extends BaseMapper<Employee> {
}
