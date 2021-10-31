package com.shf.test;

import com.shf.dao.EmpMapper;
import com.shf.bean.Employee;
import com.shf.dao.EmpMapperAnnotation;
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

//sqlsession代表和数据库的一次会话 用完必须关闭
//sqlsession和connection一样是线程非安全。每次使用都应该去获取新的对象
//mapper接口没有实现类 但是mybatis会为这个接口生成一个代理对象 将接口和xml进行绑定
//mybatis的全部配置文件  包含数据库连接池信息、事务管理器信息
//sql映射文件 保存了每一个sql语句映射信息
public class MyBatisTest {
    @Test
    public void test10(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        Map<Integer, Employee> lastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("tom");
        System.out.println(lastNameLikeReturnMap);
        openSession.close();
    }

    @Test
    public void test9(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
        System.out.println(map);
        openSession.close();
    }

    @Test
    public void test8(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        List<Employee> nameLike = mapper.getEmpsByLastNameLike("tom");
        for (Employee employee : nameLike) {
            System.out.println(employee);
        }
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test7(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("lastName","tom");
        map.put("tableName","tbl_employee");
        Employee emp = mapper.getEmpByMap(map);
        System.out.println(emp);
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test6(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        Employee emp = mapper.getEmpByIdAndLastName(1, "tom");
        System.out.println(emp);
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test5(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        boolean deleteEmp = mapper.deleteEmp(3);
        System.out.println(deleteEmp);
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test4(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        boolean updateEmp = mapper.updateEmp(new Employee(1, "tom", "jerry@qqqq.com", "1"));
        System.out.println(updateEmp);
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test3(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        获取到的sqlsession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        Employee newEmp = new Employee(null, "jerry", "jerry@qqqq.com", "1");
        Long addEmp = mapper.addEmp(newEmp);
        System.out.println(addEmp);
        System.out.println("newEmp.id="+newEmp);
//        手动提交
        openSession.commit();
        openSession.close();
    }

    @Test
    public void test2test(){
        SqlSession openSession = getSqlSessionFactory().openSession();
        EmpMapperAnnotation mapper = openSession.getMapper(EmpMapperAnnotation.class);
        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);
    }

    @Test
    public void test1(){
//        2.获取sqlsession对象
        SqlSession openSession = getSqlSessionFactory().openSession();
//        3.获取接口的实例对象
//            会为接口自动创建一个代理对象 代理对象去执行增删改查
        EmpMapper mapper = openSession.getMapper(EmpMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
        System.out.println(mapper.getClass()); //class com.sun.proxy.$Proxy4
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

    @Test
    public void test() throws IOException {
//        1.根据xml配置文件 创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession openSession = sqlSessionFactory.openSession();
        //传入参数查询，返回结果
        Employee selectOne = openSession.selectOne("com.shf.mybatis.Employee.selectEmp", 1);
        System.out.println(selectOne);
        //关闭session
        openSession.close();
    }
}
