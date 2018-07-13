package com.demo.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建人是: zsg 创建时间: 2017/11/16 0016.
 */
public class AppConsumer {

    private static final String url = "tcp://192.168.0.108:61616";
    private static final String topicName = "topic-test";

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
        Destination destination = session.createTopic(topicName);

        //6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("------ 接收的消息是 ------ " + textMessage.getText());
                } catch (JMSException e) {
                    System.out.println("-------- text error -------");
                }
            }
        });

        //8.关闭连接
        //connection.close();
    }
}
