package org.barcamp.rabbitmq.defaultt;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.barcamp.rabbitmq.utils.ConnectionManager;

public class MessagePublisher {

    private static final String DEFAULT_QUEUE  = "task_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionManager.getConnection();
        Channel channel = connection.createChannel();

        for (int i = 0; i < 4; i++) {
            String message = "Getting started with rabbitMQ - Msg" + i;
            channel.basicPublish("", DEFAULT_QUEUE, null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
