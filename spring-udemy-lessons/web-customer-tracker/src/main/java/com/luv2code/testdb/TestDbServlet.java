package com.luv2code.testdb;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@javax.servlet.annotation.WebServlet(name = "TestDbServlet", urlPatterns = "/testdb")
public class TestDbServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //setup connection variables
        String user = "springstudent";
        String password = "springstudent";

        String jdbcUrl = "jdbc:h2:C:/JavaLessons/JavaWeb/java-web/spring-udemy-lessons/web-customer-tracker/database/web_customer_tracker";
        String driver = "org.h2.Driver";

        try (PrintWriter out = response.getWriter()) {
            out.println("Connecting to database: " + jdbcUrl);

            Class.forName(driver);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);

            out.println("SUCCESS!!!");

            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        //get connection to database
    }
}
