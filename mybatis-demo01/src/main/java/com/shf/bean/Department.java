package com.shf.bean;

public class Department {
    private String id;
    private String dept_name;

    public Department() {
    }

    public Department(String id, String dept_name) {
        this.id = id;
        this.dept_name = dept_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
