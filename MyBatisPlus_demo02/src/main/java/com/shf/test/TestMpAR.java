package com.shf.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shf.beans.EmployeeAR;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestMpAR {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

//    AR 分页复杂操作
    @Test
    public void testARPage(){
        EmployeeAR employeeAR = new EmployeeAR();
        Page<EmployeeAR> employeeARPage = employeeAR.selectPage(new Page<>(1, 1),
                new EntityWrapper<EmployeeAR>()
                        .like("last_name", "t"));
        List<EmployeeAR> emps = employeeARPage.getRecords();
        System.out.println(emps);
    }

//    AR删除操作  删除不成功逻辑上也是成功的
    @Test
    public void testARDelete(){
        EmployeeAR employeeAR = new EmployeeAR();
        boolean result = employeeAR.delete(new EntityWrapper<EmployeeAR>()
                .like("last_name", "t"));
        System.out.println("result:"+result);
    }
    @Test
    public void testARDeleteById(){
        EmployeeAR employeeAR = new EmployeeAR();
//        employeeAR.setId(14);
        boolean result = employeeAR.deleteById(2);
        System.out.println("result:"+result);
    }

    //    AR查询操作
    @Test
    public void testARSelectList(){
        EmployeeAR employeeAR = new EmployeeAR();
        List<EmployeeAR> employeeARS = employeeAR.selectList(new EntityWrapper<EmployeeAR>()
                .like("last_name", "苍老师"));
        System.out.println("employeeARS:"+employeeARS);
    }
    @Test
    public void testARSelectAll(){
        EmployeeAR employeeAR = new EmployeeAR();
        List<EmployeeAR> employeeARS = employeeAR.selectAll();
        System.out.println("employeeARS:"+employeeARS);
    }
    @Test
    public void testARSelectById(){
        EmployeeAR employeeAR = new EmployeeAR();
//        employeeAR.setId(14);
        EmployeeAR emp = employeeAR.selectById(14);
        System.out.println("emp:"+emp);
    }

    //    AR插入操作
    @Test
    public void testARUpdate(){
        EmployeeAR employeeAR = new EmployeeAR();
        employeeAR.setId(14);
        employeeAR.setLastName("宋老师1");
        employeeAR.setEmail("sls1@ee.com");
        employeeAR.setGender(12);
        employeeAR.setGender(351);
        boolean result = employeeAR.updateById();
        System.out.println("result:"+result);
    }

    //    AR插入操作
    @Test
    public void testARInsert(){
        EmployeeAR employeeAR = new EmployeeAR();
        employeeAR.setLastName("宋老师");
        employeeAR.setEmail("sls@ee.com");
        employeeAR.setGender(1);
        employeeAR.setGender(35);
        boolean result = employeeAR.insert();
        System.out.println("result:"+result);
    }
}
