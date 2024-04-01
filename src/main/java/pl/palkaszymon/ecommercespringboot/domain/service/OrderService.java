package pl.palkaszymon.ecommercespringboot.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.palkaszymon.ecommercespringboot.database.entity.OrderEntity;
import pl.palkaszymon.ecommercespringboot.database.repository.OrderRepository;
import pl.palkaszymon.ecommercespringboot.domain.model.OrderStatus;
import pl.palkaszymon.ecommercespringboot.domain.model.ShippingMethod;
import pl.palkaszymon.ecommercespringboot.domain.request.NewOrderRequest;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderEntity createOrder(NewOrderRequest request) {
        OrderEntity orderToSave = new OrderEntity(null, request.email(), LocalDateTime.now(),
                ShippingMethod.fromString(request.shippingMethod()), OrderStatus.PROCESSING, request.productIds());
        return orderRepository.save(orderToSave);
    }
}
