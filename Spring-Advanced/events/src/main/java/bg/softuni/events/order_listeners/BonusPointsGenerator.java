package bg.softuni.events.order_listeners;

import bg.softuni.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonusPointsGenerator {
    private static Logger LOGGER = LoggerFactory.getLogger(BonusPointsGenerator.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Order with No- {} has been created! " +
                        "I'm going to calculate bonus points",
                orderCreatedEvent.getOrderId());
    }
}
