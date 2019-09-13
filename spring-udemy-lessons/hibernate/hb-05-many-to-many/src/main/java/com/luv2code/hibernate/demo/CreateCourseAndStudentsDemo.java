package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();

                Course tempCourse = new Course("Pacman - How To Score One Million Points");

                System.out.println("Saving course ...");
                session.save(tempCourse);
                System.out.println("Saved the course: " + tempCourse);

                Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
                Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

                tempCourse.addStudent(tempStudent1);
                tempCourse.addStudent(tempStudent2);

                System.out.println("\nSaving students ...");
                session.save(tempStudent1);
                session.save(tempStudent2);
                System.out.println("Saved students: " + tempCourse.getStudents());

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
