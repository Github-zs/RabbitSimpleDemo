package rabbit.t1SimpleMode;/**
 * Talcloud.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */


import java.io.IOException;

/**
 * <pre>
 *    描述信息
 * </pre>
 *
 * @version $Id: Run.java, v1.0 2019/8/29 17:09 86130 Exp $
 */
public class Run {
    public static void main(String[] args) {
        RabbitSend send = new RabbitSend();
        RabbitRecv recv = new RabbitRecv();

        try {
            send.sendMQ();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            recv.recvMQ();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
