package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory()) {

            //create session
            Session session = factory.getCurrentSession();
            try {
                /*Instructor tempInstructor = new Instructor(
                        "Chad",
                        "Darby",
                        "darby@luv2code.com");

                InstructorDetail tempInstructorDetail = new InstructorDetail(
                        "http://www.luv2code.com/youtube",
                        "Luv 2 code!!!");*/

                Instructor tempInstructor = new Instructor(
                        "Madhu",
                        "Patel",
                        "madhu@luv2code.com");

                InstructorDetail tempInstructorDetail = new InstructorDetail(
                        "http://www.youtube.com",
                        "Guitar");

                tempInstructor.setInstructorDetail(tempInstructorDetail);

                session.beginTransaction();

                System.out.println("Saving instructor: " + tempInstructor);
                session.save(tempInstructor);

                session.getTransaction().commit();

                System.out.println("Done!");
            } finally {
                session.close();
            }
        }
    }
}
