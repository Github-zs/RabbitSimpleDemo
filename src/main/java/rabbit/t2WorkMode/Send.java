package rabbit.t2WorkMode;/**
 * Talcloud.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.RabbitUtils;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: Send.java, v1.0 2019/8/29 17:21 86130 Exp $
 */
public class Send {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitUtils.getRabbitConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}
