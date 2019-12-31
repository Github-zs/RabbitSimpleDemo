package rabbit.t2WorkMode;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import rabbit.RabbitUtils;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: Recv2.java, v1.0 2019/8/29 17:21 86130 Exp $
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitUtils.getRabbitConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者,work模式下这样设置可以实现按劳分配，而不是轮询分发（每个消费者获得同样的消息数，无论等待消费能力是否相同）
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // 休眠1秒
            Thread.sleep(1000);
            /**
             * 实现公平分发需要手动确认，消费者从队列中获取消息后，服务器会将该消息标记为不可用状态,
             *    等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态
             */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
