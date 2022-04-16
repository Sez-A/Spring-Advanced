package bg.softuni.schedules.schedulers;

import bg.softuni.schedules.config.DBInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronSchedulerDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);
    private final DBInitializer initializer;

    public CronSchedulerDemo(DBInitializer initializer) {
        this.initializer = initializer;
    }

    /*
    TODO This cron expression will execute only at 19:00:00 Once and will execute again tomorrow at 19:00:00
     if you want to execute this on specific date you can configure additionally example
     cron = "0 0 19 15 1 *" -> this will execute at 19:00:00 on 15 January
     You can use cron like this example above or
     like this bellow but before you able to be use like this bellow
     you should to configure job time in application yml
     look in application.yml for better understanding

     */
    @Scheduled(cron = "${schedulers.cron}")
    public void cronScheduler() {
        LOGGER.info("Hello from cron scheduler at 19:00:00 every day");
        // this.initializer.initializeDB();
    }
}
