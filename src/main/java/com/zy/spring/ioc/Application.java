package com.zy.spring.ioc;

import com.zy.spring.ioc.context.ApplicationContext;

import com.zy.spring.ioc.context.ClassPathXmlApplicationContext;
import com.zy.spring.ioc.entity.Apple;



public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Apple apple = (Apple)context.getBean("sweetApple");
        System.out.println(apple);
    }
}
