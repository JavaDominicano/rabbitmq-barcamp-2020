package org.barcamp.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.barcamp.rabbitmq.utils.ConnectionManager;

import java.util.concurrent.atomic.AtomicInteger;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        AtomicInteger atomic = new AtomicInteger(1);

        try (Connection connection = ConnectionManager.getConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);


            for (; ; ) {
                Thread.sleep(2000);
                String message = "Bar Camp 2020, task #: " + atomic.getAndIncrement();
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
