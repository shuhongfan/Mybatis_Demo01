package com.shf.test;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shf.beans.Employee;
import com.shf.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestMp {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);

//    条件构造器 Condition
    @Test
    public void testEntityWrapperCondition(){
        List<Employee> employees = employeeMapper
                .selectPage(new Page<Employee>(1, 2),
                        Condition.create()
                                .between("age",18,50)
                                .eq("gender","1")
                                .eq("last_name","tom"));
        System.out.println(employees);
    }

    //    条件构造器 分页排序查询
    @Test
    public void testEntityWrapperSelectList2(){
//        查询性别为女，根据age进行排序 asc/desc  简单分页
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 0)
                .orderBy("age")
//                .orderDesc(Arrays.asList(new String[]{"age"})));
                .last("desc limit 1,2"));
        System.out.println(employees);
    }

    //    条件构造器 删除操作
    @Test
    public void testEntityWrapperDelete(){
        Integer result = employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("last_name", "tom")
                .eq("age", "45"));
        System.out.println(result);
    }

//    条件构造器  修改操作
    @Test
    public void testEntityWapperUpdate(){
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cls@qq.com");
        employee.setAge(0);
        Integer result = employeeMapper.update(employee, new EntityWrapper<Employee>()
                .eq("last_name", "tom")
                .eq("age", 45));
        System.out.println(result);
    }

//    条件构造器  查询操作
    @Test
    public void testEntityWrapperSelectList(){
//        性别为女并且名字中带有老师 或者 邮箱中带有 “a"
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 0)
                .like("last_name", "老师")
                .orNew()
                .like("email", "a"));
        System.out.println(employees);
    }
    @Test
    public void testEntityWrapperSelectPage(){
//        分页查询年龄在18-50之间性别为男且姓名为Tom的所有用户
        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "tom"));
        System.out.println(employees);
    }

//    通用删除操作
//    批量删除
    @Test
    public void testCommonDeleteBatchIds(){
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(10);
        idList.add(11);
        idList.add(12);
        Integer result = employeeMapper.deleteBatchIds(idList);
        System.out.println("result="+result);
    }
//    根据条件删除数据
    @Test
    public void testCommonDeleteByMap(){
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("last_name","MP");
        columnMap.put("email","mp@Wqq.com");
        Integer result = employeeMapper.deleteByMap(columnMap);
        System.out.println("result="+result);
    }
    //    根据id删除数据
    @Test
    public void testCommonDelete(){
        Integer result = employeeMapper.deleteById(7);
        System.out.println("result="+result);
    }

//    通用查询操作
//    5.分页查询
    @Test
    public void testCommonSelectPage(){
        List<Employee> employees = employeeMapper.selectPage(new Page<>(2,2),null);
        System.out.println(employees);
    }
    //    4.通过Map封装条件查询
    @Test
    public void testCommonSelectByMap(){
        HashMap<String, Object> colmnMap = new HashMap<>();
        colmnMap.put("last_name","Tom");
        colmnMap.put("gender",1);
        List<Employee> employees = employeeMapper.selectByMap(colmnMap);
        System.out.println(employees);
    }
    //    3.通过多个id查询
    @Test
    public void testCommonSelectBatchIds(){
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(4);
        idList.add(5);
        idList.add(10);
        List<Employee> employees = employeeMapper.selectBatchIds(idList);
        System.out.println(employees);
    }
//    2.通过多个列进行查询一条数据
    @Test
    public void testCommonSelectOne(){
        Employee emp = new Employee();
        emp.setId(11);
        emp.setLastName("mybatisPlus");
        Employee employee = employeeMapper.selectOne(emp);
        System.out.println(employee);
    }
//    1.通过id查询
    @Test
    public void testCommonSelectById(){
        Employee employee = employeeMapper.selectById(11);
        System.out.println(employee);
    }

//    通用更新操作
    @Test
    public void testCommonUpdate(){
//        初始化修改对象
        Employee employee = new Employee();
        employee.setId(11);
        employee.setLastName("mybatisPlus");
        employee.setEmail("mybatisPlus@q.cm");
        employee.setGender(1);
//        employee.setAge(33);
//        Integer result = employeeMapper.updateById(employee);
        Integer result = employeeMapper.updateAllColumnById(employee);
        System.out.println("result="+result);
    }

//    通用插入操作
    @Test
    public void testCommonInsert(){
//        初始化Employee对象
        Employee employee = new Employee();
        employee.setLastName("mp");
//        employee.setEmail("mp@Wqq.com");
//        employee.setGender(1);
        employee.setAge(22);
        employee.setSalary(20000.00);
//        插入到数据库

//        insert方法在插入时，会根据实体类的每个属性值进行非空判读，只有非空的属性对应的字段才会出现到SQL语句中国
//        Integer result = employeeMapper.insert(employee);

//        insertAllColumn方法在插入时，不管属性是否为空，属性对应的字段都会出现在SQL语句中
        Integer result = employeeMapper.insertAllColumn(employee);

        System.out.println("result="+result);

//        获取新插入的主键id值
        Integer id = employee.getId();
        System.out.println("id="+id);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource ds = ioc.getBean("dataSource", DataSource.class);
        System.out.println(ds);

        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
