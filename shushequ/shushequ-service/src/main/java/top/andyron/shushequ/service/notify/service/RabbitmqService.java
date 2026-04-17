package top.andyron.shushequ.service.notify.service;

import com.rabbitmq.client.BuiltinExchangeType;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author andyron
 * @date 2026/4/17
 */
public interface RabbitmqService {

    boolean enabled();

    /**
     * 发布消息
     *
     * @param exchange
     * @param exchangeType
     * @param toutingKey
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    void publishMsg(String exchange,
                    BuiltinExchangeType exchangeType,
                    String toutingKey,
                    String message);


    /**
     * 消费消息
     *
     * @param exchange
     * @param queue
     * @param routingKey
     * @throws IOException
     * @throws TimeoutException
     */
    void consumerMsg(String exchange,
                     String queue,
                     String routingKey) throws IOException, TimeoutException;


    void processConsumerMsg();
}
