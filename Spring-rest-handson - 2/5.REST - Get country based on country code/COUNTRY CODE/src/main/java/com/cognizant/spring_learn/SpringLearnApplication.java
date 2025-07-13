package com.cognizant.spring_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        System.out.println("Country list loaded: " + context.getBean("countryList"));
    }
}
