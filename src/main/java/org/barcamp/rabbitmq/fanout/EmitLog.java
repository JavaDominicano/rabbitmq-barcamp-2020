package org.barcamp.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.barcamp.rabbitmq.utils.ConnectionManager;

import java.util.concurrent.atomic.AtomicInteger;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        AtomicInteger atomic = new AtomicInteger(1);

        try (Connection connection = ConnectionManager.getConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            for (; ; ) {
                Thread.sleep(2000);
                String message = "Bar Camp 2020, task #: " + atomic.getAndIncrement();

                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
