###通配符模式(一个生产者，一个交换机，多个队列，多个消费者)：
    基于路由模式，在路由模式中，队列所绑定的key与消息发送时所指定的key进行匹配，
    匹配成功的队列获取相遇message。
    通配符模式下，消息发送时还是指定一个key值，但是队列可以使用通配符进行匹配。
    可以使用*.key,key.*,*.*等多种形式进行匹配。