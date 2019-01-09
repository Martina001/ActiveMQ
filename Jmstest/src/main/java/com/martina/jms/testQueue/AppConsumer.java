package com.martina.jms.testQueue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    private static final String url="tcp://127.0.0.1:61616";
    private static  final String queueName="queue-test";

    public static void main(String[] args) throws JMSException {
        //创建ConnectionFactory

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);

        //创建Connection
        Connection connection=connectionFactory.createConnection();

        //启动连接
        connection.start();
        //创建会话
         /* 在connection创建session的时候，第一个参数决定session事物是否开启，true时session事物开启。为false时 ，session事物关闭。
        Session.AUTO-ACKNOWLEDGE,session负责发送过去消息，收到自动确认。
        Session.CLIENT-ACKNOWLEDGE(接收端确认接收到这条消息，调用这条消息的确认方法)。
        session.DUPS_OK_ACKNOWLEDGE（懒散的确认方法，这种确认方法有可能传递的一些复制信息会出错）。 
        */
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建一个目标

        Destination destination=session.createQueue(queueName);

        //创建一个消费者
        final MessageConsumer consumer=session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage) message;
                try{
                    System.out.println("接收消息"+textMessage.getText());
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });


        //最后关闭连接
        //注意这里:当开始时候就直接关闭的话，上面的消息就不会输出，所以要注释掉这一行
     //   connection.close();

    }
}
