package com.luv2code.springboot.hibernatewithservicedemo21.dao;

import com.luv2code.springboot.hibernatewithservicedemo21.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
