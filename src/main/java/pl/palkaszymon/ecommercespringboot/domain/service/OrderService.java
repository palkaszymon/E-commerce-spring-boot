package pl.palkaszymon.ecommercespringboot.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.palkaszymon.ecommercespringboot.database.entity.OrderEntity;
import pl.palkaszymon.ecommercespringboot.database.repository.OrderRepository;
import pl.palkaszymon.ecommercespringboot.database.repository.ProductRepository;
import pl.palkaszymon.ecommercespringboot.domain.exception.ProductNotFoundException;
import pl.palkaszymon.ecommercespringboot.domain.model.OrderStatus;
import pl.palkaszymon.ecommercespringboot.domain.model.ShippingMethod;
import pl.palkaszymon.ecommercespringboot.domain.request.NewOrderRequest;
import pl.palkaszymon.ecommercespringboot.domain.validator.OrderRequestValidator;
import pl.palkaszymon.ecommercespringboot.messaging.RabbitMQService;
import pl.palkaszymon.ecommercespringboot.messaging.messages.CustomerNotificationServiceMessage;
import pl.palkaszymon.ecommercespringboot.messaging.messages.NewOrderMessage;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RabbitMQService rabbitMQService;
    private final OrderRequestValidator orderRequestValidator;

    @Transactional
    public OrderEntity createOrder(NewOrderRequest request) {
        orderRequestValidator.validate(request);
        checkRequestProductsExistence(request.productIds());
        OrderEntity orderToSave = new OrderEntity(null, request.email(), request.address(), LocalDateTime.now(),
                ShippingMethod.fromString(request.shippingMethod()), OrderStatus.PROCESSING, request.productIds());
        OrderEntity savedOrder = orderRepository.save(orderToSave);
        sendOrderNotifications(savedOrder);
        return savedOrder;
    }

    private void checkRequestProductsExistence(Set<Long> productIds) {
        productIds.forEach(productId -> productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException(String.format("Product with ID: %s not found", productId))));
    }

    private void sendOrderNotifications(OrderEntity savedOrder) {
        rabbitMQService.sendOrderNotificationToWarehouse(new NewOrderMessage(savedOrder.getId()));
        rabbitMQService.sendOrderNotificationToCustomerNotificationService(new CustomerNotificationServiceMessage(
                savedOrder.getId(), savedOrder.getEmail(), savedOrder.getShippingMethod().getName()));
    }
}
