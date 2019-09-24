package rabbit;/**
 * Talcloud.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: RabbitUtils.java, v1.0 2019/8/29 17:05 86130 Exp $
 */
public class RabbitUtils {

    public static Connection getRabbitConnection() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("admin");
        factory.setPassword("admin");
        
        Connection connection = factory.newConnection();

        return connection;
    }
}
