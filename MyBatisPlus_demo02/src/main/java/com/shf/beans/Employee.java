package com.shf.beans;

//JavaBean  定义JavaBean中成员变量所使用的类型  使用包装类型 默认值null
//    因为每个基本类型都有一个默认值：
//            int==>0
//            boolean==>false

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


//MybatisPlus会默认使用实体类的类名到数据库中找对应的表
//@TableName("tbl_employee")
public class Employee {
//    @TableId
//        value:指定表中的主键列的列名，如果试题属性与列名一致，可以省略不指定
//        type：自增策略
//    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
//    @TableField(value = "last_name")
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

//    exist忽略字段
    @TableField(exist = false)
    private Double Salary;

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
