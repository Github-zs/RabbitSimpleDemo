package rabbit.t4RouterMode;/**
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
 * @version $Id: Send.java, v1.0 2019/8/29 17:57 86130 Exp $
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitUtils.getRabbitConnection();
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = "查询";
        //"delete"为消息key，用来做路由匹配
        channel.basicPublish(EXCHANGE_NAME, "select", null, message.getBytes());

        channel.close();
        connection.close();
    }

}
