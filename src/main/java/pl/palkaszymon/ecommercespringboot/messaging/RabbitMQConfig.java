package pl.palkaszymon.ecommercespringboot.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue createWarehouseNotificationQueue() {
        return new Queue("q.warehouse-notifications");
    }

    @Bean
    public Queue createCustomerNotificationServiceQueue() {
        return new Queue("q.customer-service-notifications");
    }
}
