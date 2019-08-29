package rabbit.t1SimpleMode;/**
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
 * @version $Id: RabbitRecv.java, v1.0 2019/8/29 17:08 86130 Exp $
 */
public class RabbitRecv {
    public void recvMQ() throws IOException, InterruptedException {
        Connection connection = RabbitUtils.getRabbitConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("q_test_01", false, false, false, null);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume("q_test_01", true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("get message:" + message);

        }
    }
}
