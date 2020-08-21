package io.botcrafting.botcraft.model.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static io.botcrafting.botcraft.configuration.constant.UrlConstant.BOTCRAFT_API_BASE_URL;

@Service
public class ScheduleJobsTask {
    @Autowired
    private RestTemplate restTemplate;


    //@Scheduled(fixedRate = 1000)
    @Scheduled(cron = "0/1 0/1 0 ? * * *")
    public void keepAlive() {
        String url = BOTCRAFT_API_BASE_URL + "/keep-alive";
        System.out.println("I'm calling rest template on: " + url);
        restTemplate.getForEntity(url, String.class);
    }
}
