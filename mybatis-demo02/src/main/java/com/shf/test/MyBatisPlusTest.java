package com.shf.test;

import com.shf.bean.Department;
import com.shf.bean.Employee;
import com.shf.dao.DepartmentMapper;
import com.shf.dao.EmpMapper;
import com.shf.dao.EmpMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MyBatisPlusTest {
    @Test
    public void test6(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        Department deptByIdStep = mapper.getDeptByIdStep(1);
        System.out.println(deptByIdStep.getId());
        System.out.println(deptByIdStep);
        openSession.close();
    }

    @Test
    public void test5(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        Department deptByIdPlus = mapper.getDeptByIdPlus(1);
        System.out.println(deptByIdPlus);
        openSession.close();
    }

    @Test
    public void test4(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapperPlus mapper = openSession.getMapper(EmpMapperPlus.class);
        Employee empByIdStep = mapper.getEmpByIdStep(1);
//        System.out.println(empByIdStep.getId());
        System.out.println(empByIdStep);
        openSession.close();
    }

    @Test
    public void test3(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapperPlus mapper = openSession.getMapper(EmpMapperPlus.class);
        Employee empAndDept = mapper.getEmpAndDept(1);
        System.out.println(empAndDept);
        openSession.close();
    }

    @Test
    public void test2(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapperPlus mapper = openSession.getMapper(EmpMapperPlus.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
        openSession.close();
    }

    @Test
    public void test1(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapperPlus mapper = openSession.getMapper(EmpMapperPlus.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
        openSession.close();
    }

    public SqlSessionFactory getSqlSessionFactory(){
        //        1.获取sqlsessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
