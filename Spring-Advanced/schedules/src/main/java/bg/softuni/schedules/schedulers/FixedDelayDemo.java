package bg.softuni.schedules.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void fixedRateDemo() {
        LOGGER.info("Print in every 10 seconds with fixed delay scheduler");
    }
}
