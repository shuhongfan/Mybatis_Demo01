package com.shf.test;

import com.shf.bean.Department;
import com.shf.bean.Employee;
import com.shf.dao.EmpMapperPlus;
import com.shf.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeMapperDynamicSQLTest {
    @Test
    public void testSecondLevelCache(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        SqlSession openSession2 = sqlSessionFactory.openSession(true);
//        二级缓存的使用
//        1.开启全局二级缓存配置  <setting name="cacheEnabled" value="true"/>
//        2.去mapper.xml中配置使用二级缓存  <cache eviction="FIFO" flushInterval="60000" readOnly="false" size="1024" />
//        3. POJO实验序列化接口

//        查出的数据都会被默认先放在一级缓存中,只有会话提交或者关闭以后,一级缓存中的数据才会转移到二级缓存中
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        EmployeeMapperDynamicSQL mapper2 = openSession2.getMapper(EmployeeMapperDynamicSQL.class);

        List<Employee> employees = mapper.getEmpsTestInnerParameters(new Employee(null, "o"));
        openSession.close();

        List<Employee> employees2 = mapper2.getEmpsTestInnerParameters(new Employee(null, "o"));
        System.out.println(employees);
        System.out.println(employees2);
        System.out.println(employees2==employees);

        openSession2.close();
    }
    @Test
    public void test9(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
//        一级缓存 本地缓存 sqlsession级别的缓存 一级缓存是一直开启的
//        二级缓存  基于namespace基本的缓存;一个namespace对应一个二级缓存
//            一个会话,查询一条数据,这个数据就会被放在当前会话的一级缓存中
//        如果会话关闭 一级缓存中的数据就会被保存到二级缓存中;新的会话查询信息,就可以了参照二级缓存
//        不同namespace查出的数据就会放在自己对应的缓存中
        List<Employee> employees = mapper.getEmpsTestInnerParameters(new Employee(null, "o"));
        System.out.println(employees);
//        清除缓存
        openSession.clearCache();
        List<Employee> employees2 = mapper.getEmpsTestInnerParameters(new Employee(null, "o"));
        System.out.println(employees2);
        System.out.println(employees==employees2);
        openSession.close();
    }

    @Test
    public void test8(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null,"smith","smith@qq.com","1",new Department(1)));
        employees.add(new Employee(null,"allen","allen@qq.com","0",new Department(2)));
        mapper.addEmps2(employees);
        openSession.close();
    }

    @Test
    public void test7(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null,"smith","smith@qq.com","1",new Department(1)));
        employees.add(new Employee(null,"allen","allen@qq.com","0",new Department(2)));
        mapper.addEmps(employees);
        openSession.close();
    }

    @Test
    public void test6(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        List<Employee> empByConditionForeach = mapper.getEmpByConditionForeach(Arrays.asList(1,2,3,4));
        System.out.println(empByConditionForeach);
        openSession.close();
    }

    @Test
    public void test5(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee(1,"admin");
        mapper.updateEmp2(employee);
        openSession.close();
    }

    @Test
    public void test4(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee(1,"admin");
        mapper.updateEmp(employee);
        openSession.close();
    }

    @Test
    public void test3(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee();
        List<Employee> empsByConditionIfgetEmpty = mapper.getEmpsByConditionChoose(employee);
        System.out.println(empsByConditionIfgetEmpty);
        openSession.close();
    }

    @Test
    public void test2(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee(null, "%t%","%@%",null);
        List<Employee> empsByConditionIfgetEmpty = mapper.getEmpsByConditionTrim(employee);
        System.out.println(empsByConditionIfgetEmpty);
        openSession.close();
    }

    @Test
    public void test1(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        Employee employee = new Employee(null, "%t%","%@%",null);
        List<Employee> empsByConditionIfgetEmpty = mapper.getEmpsByConditionIf(employee);
        System.out.println(empsByConditionIfgetEmpty);
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
