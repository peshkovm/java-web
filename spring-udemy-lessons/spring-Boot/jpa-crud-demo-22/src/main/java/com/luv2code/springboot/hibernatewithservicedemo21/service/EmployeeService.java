package com.luv2code.springboot.hibernatewithservicedemo21.service;

import com.luv2code.springboot.hibernatewithservicedemo21.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
