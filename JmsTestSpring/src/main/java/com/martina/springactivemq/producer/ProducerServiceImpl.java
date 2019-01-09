package com.martina.springactivemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

public class ProducerServiceImpl implements ProducerService{
    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name="topicDestination")
    Destination destination;



    public void setDestination(Destination destination) {

        this.destination = destination;
    }

    @Override
    public void sendMessage(final String message) {
//        使用JMSTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage=session.createTextMessage(message);
                return textMessage;
            }
        });
        System.out.println("发送消息"+ message);

    }
}
