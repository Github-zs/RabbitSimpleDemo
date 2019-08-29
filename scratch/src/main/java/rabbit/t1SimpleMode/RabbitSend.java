package rabbit.t1SimpleMode;/**
 * Talcloud.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.RabbitUtils;

import java.io.IOException;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: RabbitSend.java, v1.0 2019/8/29 17:07 86130 Exp $
 */
public class RabbitSend {

    public void sendMQ() throws IOException {
        Connection connection = RabbitUtils.getRabbitConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("q_test_01", false, false, false, null);

        String message = "hello world";

        channel.basicPublish("", "q_test_01", null, message.getBytes());
        channel.close();
        connection.close();
    }
}
