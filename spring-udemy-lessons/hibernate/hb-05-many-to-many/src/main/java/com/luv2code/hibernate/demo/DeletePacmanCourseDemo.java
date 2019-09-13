package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();

                int courseId = 1;

                Course tempCourse = session.get(Course.class, courseId);

                System.out.println("Deleting course: " + tempCourse);

                session.delete(tempCourse);

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
