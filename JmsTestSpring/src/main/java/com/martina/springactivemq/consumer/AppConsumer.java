package com.martina.springactivemq.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.TextMessage;

public class AppConsumer {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
    }
}
