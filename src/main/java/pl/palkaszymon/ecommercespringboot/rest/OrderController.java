package pl.palkaszymon.ecommercespringboot.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.palkaszymon.ecommercespringboot.database.entity.OrderEntity;
import pl.palkaszymon.ecommercespringboot.domain.request.NewOrderRequest;
import pl.palkaszymon.ecommercespringboot.domain.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderEntity createOrder(@RequestBody NewOrderRequest request) {
        return orderService.createOrder(request);
    }
}
