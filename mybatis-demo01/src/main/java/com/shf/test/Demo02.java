package com.shf.test;

import com.shf.bean.Employee;
import com.shf.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Demo02 {
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test1() throws IOException {
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);

        Employee empById = mapper.getEmpById(3);
        System.out.println(empById);

        openSession.close();
    }

    @Test
    public void test2() throws IOException {
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);

        Employee empAndDept = mapper.getEmpAndDept(3);
        System.out.println(empAndDept);
        System.out.println(empAndDept.getDept());

        openSession.close();
    }
}
