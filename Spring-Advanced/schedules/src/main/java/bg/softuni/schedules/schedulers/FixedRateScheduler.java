package bg.softuni.schedules.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedRateScheduler {
    private static Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);

    @Scheduled(fixedRate = 5000)
    public void fixedRateDemo() {
        LOGGER.info("Print in every 5 seconds with fixed rate scheduler");
    }
}
