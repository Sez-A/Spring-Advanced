package bg.softuni.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerTest {
    private Logger LOGGER = LoggerFactory.getLogger(ApplicationListenerTest.class);

    @EventListener(SpringApplicationEvent.class)
    public void onApplicationEvent(SpringApplicationEvent event) {
        LOGGER.info("I have received an Event: {}", event);
    }
}
