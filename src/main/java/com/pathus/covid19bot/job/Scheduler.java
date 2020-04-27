package com.pathus.covid19bot.job;

import com.pathus.covid19bot.service.ITwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private ITwitterService twitterService;

    @Scheduled(cron = "${bot.cron}")
    public void cronJobSch() {

        twitterService.processPostTweet();
    }
}