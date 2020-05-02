package com.pathus.covid19bot.service.impl;

import com.pathus.covid19bot.model.Statistics;
import com.pathus.covid19bot.model.StatisticsOut;
import com.pathus.covid19bot.repository.IStatisticsRepository;
import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.service.ITwitterService;
import com.pathus.covid19bot.util.Constants;
import com.pathus.covid19bot.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;

@Service
public class TwitterService implements ITwitterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

    @Inject
    private final TwitterTemplate twitterTemplate;

    @Inject
    private IStatisticsRepository statisticsRepository;

    @Value("${guinea.data.url}")
    private String url;

    @Value("${hashtag}")
    private String hashtag;

    @Autowired
    private ResourceLoader resourceLoader;

    @Inject
    private final IDataService dataService;

    public TwitterService(TwitterTemplate twitterTemplate, IDataService dataService) {
        this.twitterTemplate = twitterTemplate;
        this.dataService = dataService;
    }


    @Override
    public void processPostTweet() {
        StatisticsOut statistics = dataService.getStatistics(url);
        String message = Util.createTweetMessage(statistics, hashtag);
        LOGGER.info("message du tweet {}", message);
        postTweet(message, Constants.DEFAULT_IMG_PATH);
        LOGGER.info("tweet envoyé {}", statistics.getNewStatistics().getUpdatedTime());
        updateStatistics(statistics);
    }

    private void updateStatistics(StatisticsOut statistics){
        Statistics newStatistics = statistics.getNewStatistics();
        Statistics previousStatistics = statistics.getPreviousStatistics();
        previousStatistics.setCases(newStatistics.getCases());
        previousStatistics.setDeaths(newStatistics.getDeaths());
        previousStatistics.setRecovered(newStatistics.getRecovered());
        previousStatistics.setUpdatedTime(newStatistics.getUpdatedTime());
        statisticsRepository.save(previousStatistics);
    }

    private Tweet postTweet(String tweetMessage, String imageUrl) {
        TweetData tweetData = new TweetData(tweetMessage);
        try {
            Resource resource = resourceLoader.getResource("classpath:".concat(imageUrl));
            LOGGER.info("Trying to tweet image with url {} content length {}", imageUrl, resource.contentLength());
            tweetData = tweetData.withMedia(resource);
        } catch (IOException e) {
            LOGGER.error("IOException for tweet image {}\n{}", imageUrl, e);
        }
        return twitterTemplate.timelineOperations().updateStatus(tweetData);
    }

}
