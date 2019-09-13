package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        //create session factory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            //create session
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();

                List theStudents = session.
                        createQuery("from Student").getResultList();

                displayStudents(theStudents);

                theStudents = session.
                        createQuery("from Student s where s.lastName='Doe'").getResultList();

                System.out.println("\n\nStudents who has last name of Doe");
                displayStudents(theStudents);


                theStudents = session.createQuery("from Student s where " +
                        "s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

                System.out.println("\n\nStudents who has last name of Doe " +
                        "OR first name Daffy");
                displayStudents(theStudents);

                theStudents = session.createQuery("from Student s where s.email " +
                        "like '%gmail.com'").getResultList();

                System.out.println("\n\nStudents whose email ends with gmail.com");
                displayStudents(theStudents);

                session.getTransaction().commit();

                System.out.println("Done!");
            }
        }

    }

    private static void displayStudents(List theStudents) {
        for (Object tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
