package rabbit.t5WildcardMode;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbit.RabbitUtils;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: Send.java, v1.0 2019/8/29 18:20 86130 Exp $
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitUtils.getRabbitConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 消息内容
        String message = "Hello World!!";
        channel.basicPublish(EXCHANGE_NAME, "routekey.1", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
