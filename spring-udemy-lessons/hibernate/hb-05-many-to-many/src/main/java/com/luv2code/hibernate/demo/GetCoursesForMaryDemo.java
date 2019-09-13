package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForMaryDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();

                int theId = 1;

                Student tempStudent = session.get(Student.class, theId);

                System.out.println("\nLoaded student: " + tempStudent);
                System.out.println("Course: " + tempStudent.getCourses());

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
