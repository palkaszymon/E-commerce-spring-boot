package pl.palkaszymon.ecommercespringboot.messaging.messages;

public record OrderConfirmedMessage(Long orderId, String orderStatus) {
}
