package pl.palkaszymon.ecommercespringboot.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.palkaszymon.ecommercespringboot.domain.service.OrderService;
import pl.palkaszymon.ecommercespringboot.messaging.messages.OrderConfirmedMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderConfirmationListener {
    private final String ORDER_CONFIRMATIONS_QUEUE = "q.order-confirmations";

    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CONFIRMATIONS_QUEUE)
    public void onOrderConfirmation(OrderConfirmedMessage message) {
        log.info("Received order confirmation message, orderId: {}", message.orderId());
        if (message.orderStatus().equals("CONFIRMED")) {
            orderService.updateOrderStatus(message.orderId());
        }
    }
}
