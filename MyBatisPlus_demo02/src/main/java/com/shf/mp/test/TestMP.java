package com.shf.mp.test;

import com.baomidou.mybatisplus.plugins.Page;
import com.shf.mp.beans.Employee;
import com.shf.mp.beans.User;
import com.shf.mp.mapper.EmployeeMapper;
import com.shf.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMP {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ctx.getBean("employeeMapper",EmployeeMapper.class);

    UserMapper userMapper = ctx.getBean("userMapper",UserMapper.class);

    @Test
    public void testMetaObjectHandler(){
        User user = new User();
        user.setLogicFlag(1);
        user.setName("Tom");
        userMapper.insert(user);
    }

//    测试逻辑删除
    @Test
    public void testLogicDelete(){
        Integer result = userMapper.deleteById(1);
        System.out.println("result"+result);

        User user = userMapper.selectById(1);
        System.out.println(user);
    }

//    测试自定义全局操作
    @Test
    public void testMySqlInjector(){
        int result = employeeMapper.deleteAll();
        System.out.println("result:"+result);
    }

//    测试乐观锁插件
    @Test
    public void testoptimisticLocker(){
//        更新操作
        Employee employee = new Employee();
        employee.setId(15);
        employee.setLastName("Tom");
        employee.setEmail("tom@sss.com");
        employee.setGender("1");
        employee.setAge(22);
        employee.setVersion(1);

        Integer result = employeeMapper.updateById(employee);
        System.out.println(result);
    }

//    测试性能分析插件
    @Test
    public void TestPerformanceInterceptor(){
        Employee employee = new Employee();
        employee.setLastName("玛利亚老师");
        employee.setEmail("xx@sw.com");
        employee.setGender("0");
        employee.setAge(22);

        employeeMapper.insert(employee);
    }

//    测试SQL执行分析插件
    @Test
    public void testSQLExplain(){
        employeeMapper.delete(null);  // 全表删除
    }

//    测试分页插件
    @Test
    public void testPage(){
        Page<Object> page = new Page<>(1, 2);
        List<Employee> employees = employeeMapper.selectPage(page, null);
        System.out.println(employees);

        System.out.println("=======获取分页相关的一些信息============");

        System.out.println("总条数："+page.getTotal());
        System.out.println("当前页码："+page.getCurrent());
        System.out.println("总页码："+page.getPages());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());
    }
}
