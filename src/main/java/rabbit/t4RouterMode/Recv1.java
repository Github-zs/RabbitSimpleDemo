package rabbit.t4RouterMode;/**
 * Talcloud.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import rabbit.RabbitUtils;

import java.io.IOException;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: Recv1.java, v1.0 2019/8/29 18:00 86130 Exp $
 */
public class Recv1 {

    private final static String QUEUE_NAME = "test_queue_direct_1";

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = RabbitUtils.getRabbitConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "select");

        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv1] Received '" + message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}
