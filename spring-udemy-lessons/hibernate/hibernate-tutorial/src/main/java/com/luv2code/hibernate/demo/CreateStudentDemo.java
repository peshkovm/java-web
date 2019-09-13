package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        try (Session session = factory.getCurrentSession()) {
            System.out.println("Creating new student object");
            Student tempStudent = new Student(
                    "Paul",
                    "Wall",
                    "paul@luv2code.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
