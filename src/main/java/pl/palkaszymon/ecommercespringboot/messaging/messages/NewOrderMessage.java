package pl.palkaszymon.ecommercespringboot.messaging.messages;

import java.io.Serializable;

public record NewOrderMessage(Long orderId) implements Serializable {
}
