package org.springframework.samples.jpetstore;

import examples.ExampleBean;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.samples.jpetstore.services.PetStoreServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
        PetStoreServiceImpl service = context.getBean("petStore", PetStoreServiceImpl.class);

        /*GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("services.xml",
                "daos.xml");
        context.refresh();*/

        ExampleBean exampleBean1 = context.getBean("exampleBean", ExampleBean.class);
        exampleBean1.setYears(1);
        exampleBean1.setUltimateAnswer("answer 1");

        System.out.println("exampleBean1:");
        System.out.println("ultimateAnswer= " + exampleBean1.ultimateAnswer);
        System.out.println("years=" + exampleBean1.years);

        ExampleBean exampleBean2 = context.getBean("exampleBean", ExampleBean.class);
        exampleBean2.setYears(2);
        exampleBean2.setUltimateAnswer("answer 2");

        System.out.println("exampleBean1:");
        System.out.println("ultimateAnswer= " + exampleBean1.ultimateAnswer);
        System.out.println("years=" + exampleBean1.years);

        System.out.println("exampleBean2:");
        System.out.println("ultimateAnswer= " + exampleBean2.ultimateAnswer);
        System.out.println("years=" + exampleBean2.years);

/*        ExampleBean exampleBean3 = context.getBean("exampleBean", ExampleBean.class);
        exampleBean3.setYears(3);
        exampleBean3.setUltimateAnswer("answer 3");

        System.out.println("exampleBean3:");
        System.out.println("ultimateAnswer= " + exampleBean2.ultimateAnswer);
        System.out.println("years=" + exampleBean3.years);*/
    }
}