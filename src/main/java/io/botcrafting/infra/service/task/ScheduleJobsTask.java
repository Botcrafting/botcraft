package io.botcrafting.infra.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ScheduleJobsTask {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment currentEnvironment;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void keepAlive() {
        String url = currentEnvironment.getProperty("bot_url") + "/keep-alive";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.getForEntity(url, String.class);
    }
}
