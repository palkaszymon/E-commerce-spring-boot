package pl.palkaszymon.ecommercespringboot.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.palkaszymon.ecommercespringboot.database.entity.OrderEntity;
import pl.palkaszymon.ecommercespringboot.domain.request.NewOrderRequest;
import pl.palkaszymon.ecommercespringboot.domain.service.OrderService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderEntity createOrder(@RequestBody NewOrderRequest request) {
        log.info("Saving order for email: {}", request.email());
        return orderService.createOrder(request);
    }
}
