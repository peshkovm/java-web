package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleterMaryStudentDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();

                int theId = 2;

                Student tempStudent = session.get(Student.class, theId);

                System.out.println("\nLoaded student: " + tempStudent);
                System.out.println("Courses: " + tempStudent.getCourses());

                System.out.println("\nDeleting student: " + tempStudent);

                session.delete(tempStudent);

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
