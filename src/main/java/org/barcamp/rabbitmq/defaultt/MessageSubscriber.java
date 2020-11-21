package org.barcamp.rabbitmq.defaultt;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.barcamp.rabbitmq.utils.ConnectionManager;

public class MessageSubscriber {

    private static final String DEFAULT_QUEUE = "task_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionManager.getConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (s, delivery) -> {
            System.out.println("DeliverCallback: " + new String(delivery.getBody()));
        };
        CancelCallback cancelCallback = s -> {
            System.out.println("cancelCallback: " + s);
        };
        channel.basicConsume(DEFAULT_QUEUE, true, deliverCallback, cancelCallback);

    }
}
