package com.luv2code.springboot.hibernatewithservicedemo21.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "EMAIL")
    private String email;

    public Employee() {
    }

    public Employee(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
