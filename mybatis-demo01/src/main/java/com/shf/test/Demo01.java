package com.shf.test;

import com.shf.bean.Department;
import com.shf.bean.Employee;
import com.shf.dao.EmployeeMapper;
import com.shf.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo01 {
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();
        Employee employee = openSession.selectOne("com.shf.EmployeeMapper.getEmpById", 1);
        System.out.println(employee);

        openSession.close();
    }

    @Test
    public void test2() throws IOException {
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);

        openSession.close();
    }

    @Test
    public void tets3() throws IOException {
//        获取到的SqlSession不会自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Department department = new Department("3", "部门3");
        Employee employee = new Employee(null, "jerry", "jerry@qq.com", "1",department);
        boolean addEmp = mapper.addEmp(employee);
        System.out.println(addEmp);
        System.out.println(employee.getId());

//        手动提交数据
        openSession.commit();
        openSession.close();
    }

    @Test
    public void tets4() throws IOException {
//        获取到的SqlSession不会自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Department department = new Department("3", "部门3");
        Employee employee = new Employee(1, "jerry", "jerry@qq.com", "1",department);
        boolean updateEmp = mapper.updateEmp(employee);
        System.out.println(updateEmp);

//        手动提交数据
        openSession.commit();
        openSession.close();
    }

    @Test
    public void tets5() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        boolean daleteEmpById = mapper.daleteEmpById(1);
        System.out.println(daleteEmpById);

        openSession.close();
    }

    @Test
    public void tets6() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Employee jerry = mapper.getEmpByIdAndLastName(3, "jerry");
        System.out.println(jerry);

        openSession.close();
    }

    @Test
    public void tets7() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Map<String, Object> map=new HashMap<>();
        map.put("id",4);
        map.put("lastName","jerry");
        map.put("tableName","tbl_employee");
        Employee employee = mapper.getEmpByMap(map);
        System.out.println(employee);

        openSession.close();
    }

    @Test
    public void tets8() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        List<Employee> empsByLastNameLike = mapper.getEmpsByLastNameLike("%e%");
        for (Employee employee : empsByLastNameLike) {
            System.out.println(employee);
        }

        openSession.close();
    }

    @Test
    public void tets9() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(3);
        System.out.println(empByIdReturnMap);

        openSession.close();
    }

    @Test
    public void tets10() throws IOException {
//        自动提交数据
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%e%");
        System.out.println(empByLastNameLikeReturnMap);

        openSession.close();
    }
}
