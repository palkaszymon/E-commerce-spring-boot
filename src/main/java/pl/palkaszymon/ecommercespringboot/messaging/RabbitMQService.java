package pl.palkaszymon.ecommercespringboot.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.palkaszymon.ecommercespringboot.messaging.messages.CustomerNotificationServiceMessage;
import pl.palkaszymon.ecommercespringboot.messaging.messages.NewOrderMessage;

@RequiredArgsConstructor
@Service
public class RabbitMQService {
    private final String WAREHOUSE_NOTIFICATION_QUEUE = "q.warehouse-notifications";
    private final String CUSTOMER_NOTIFICATION_SERVICE_QUEUE = "q.customer-service-notifications";

    private final RabbitTemplate rabbitTemplate;

    public void sendOrderNotificationToWarehouse(NewOrderMessage message) {
        rabbitTemplate.convertAndSend("", WAREHOUSE_NOTIFICATION_QUEUE, message);
    }

    public void sendOrderNotificationToCustomerNotificationService(CustomerNotificationServiceMessage message) {
        rabbitTemplate.convertAndSend("", CUSTOMER_NOTIFICATION_SERVICE_QUEUE, message);
    }
}
