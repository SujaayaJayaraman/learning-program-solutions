package com.cognizant.springlearn.spring_core_xml;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreXmlApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCoreXmlApplication.class);

    public static void displayCountry() {
        // ✅ Using try-with-resources to automatically close the context
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            LOGGER.debug("Country: {}", country.toString());
        }
    }

    public static void main(String[] args) {
        displayCountry();
    }
}
