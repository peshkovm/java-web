package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();

                int theid = 2;

                Student tempStudent = session.get(Student.class, theid);

                System.out.println("\nLoaded student: " + tempStudent);
                System.out.println("Course: " + tempStudent.getCourses());

                Course tempCourse1 = new Course("Rubik;s Cube - How to Speed Cube");
                Course tempCourse2 = new Course("Atari 2600 - Game Development");

                tempCourse1.addStudent(tempStudent);
                tempCourse2.addStudent(tempStudent);

                System.out.println("\nSaving the courses ...");

                session.save(tempCourse1);
                session.save(tempCourse2);

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
