package org.barcamp.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionManager {

    private static Connection connection = null;
    /**
     * Create RabbitMQ Connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                //connection = connectionFactory.newConnection("amqp://root:nw73xj36vq@192.168.0.246:5672/mdcgroup");
                connection = connectionFactory.newConnection("amqp://guest:guest@localhost:5672/");
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
