package com.shf.bean;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private int id;
    private String deptName;
    private List<Employee> emps;

    public Department() {
    }

    public Department(int id) {
        this.id = id;
    }

    public Department(int id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public Department(int id, String deptName, List<Employee> emps) {
        this.id = id;
        this.deptName = deptName;
        this.emps = emps;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
