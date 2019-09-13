package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:h2:C:/JavaLessons/JavaWeb/java-web/spring-udemy-lessons/hibernate-tutorial/src/main/resources/databases/hb_student_tracker";
        String user = "hbstudent";
        String password = "hbstudent";

        System.out.println("Connecting to database: " + jdbcUrl);

        try (Connection myConn = DriverManager.getConnection(jdbcUrl, user, password)) {

            System.out.println("Connection successful!!!");
        }
    }
}