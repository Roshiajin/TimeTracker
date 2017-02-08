package com.epam;

import com.epam.presentation.access.FormAccessObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Alexander_Gaptullin on 12/19/2016.
 */
public class Application {

    private static final String APPLICATION_CONTEXT = "spring/application-context.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
        final FormAccessObject manager = context.getBean(FormAccessObject.class);
        manager.showForm();

    }

}
