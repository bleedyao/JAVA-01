package com.bleedyao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student stu100 = (Student)context.getBean("Student100");
        System.out.println(stu100);

        ISchool school = (ISchool) context.getBean("School01");
        school.printInfo();
    }
}
