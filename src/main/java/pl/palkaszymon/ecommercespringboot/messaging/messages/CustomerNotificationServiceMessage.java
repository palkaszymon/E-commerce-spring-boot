package pl.palkaszymon.ecommercespringboot.messaging.messages;

import java.io.Serializable;

public record CustomerNotificationServiceMessage(Long orderId, String email, String shippingMethod) implements Serializable {
}
