package com.demo.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * 创建人是: zsg 创建时间: 2017/11/16 0016.
 */
public class AppProducer {

    private static final String url = "tcp://192.168.0.108:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2.创建连接
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标
        Destination destination = session.createQueue(queueName);

        //6.创建一个生产者
        MessageProducer messageProducer = session.createProducer(destination);

        System.out.println("");
        for(int i=1;i<100;i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("test:"+i);

            //8.发布消息
            messageProducer.send(textMessage);

            System.out.println("----- 发布的消息是：----- " + textMessage.getText());
        }
        //9.关闭连接
        connection.close();
    }
}
