package pl.palkaszymon.ecommercespringboot.domain.request;

import java.util.Set;

public record NewOrderRequest(String email, String address, String shippingMethod, Set<Long> productIds) {
}
