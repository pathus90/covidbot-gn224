package com.pathus.covid19bot.service.impl;

import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.service.ITwitterService;
import com.pathus.covid19bot.util.Pattern;
import com.pathus.covid19bot.util.TwitterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TwitterService implements ITwitterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

    @Inject
    private final TwitterTemplate twitterTemplate;

    @Value("${guinea.data.url}")
    private String url;

    @Value("${hashtag}")
    private String hashtag;

    @Inject
    private final IDataService dataService;

    public TwitterService(TwitterTemplate twitterTemplate, IDataService dataService) {
        this.twitterTemplate = twitterTemplate;
        this.dataService = dataService;
    }

    @Override
    public Tweet postTweetWithImage(String message, String path) {
        return twitterTemplate.timelineOperations().updateStatus(new TweetData(message).withMedia(new ClassPathResource(path)));
    }

    @Override
    public Tweet postTweet(String message) {
        return twitterTemplate.timelineOperations().updateStatus(message);
    }

    @Override
    public void getHomeTimeline() {
        List<Tweet> tweetList = twitterTemplate.timelineOperations().getUserTimeline();
        tweetList.stream().map(Tweet::getText).forEach(System.out::println);
    }

    @Override
    public void processPostTweet() {
        String now = TwitterUtil.getNow(Pattern.DATE_PATTERN_DDMMYYY_HHMMSS.getFormat());
        String message = TwitterUtil.createTweetMessage(dataService.getStatistics(url), hashtag);
        LOGGER.info("processing at {}", now);
        LOGGER.info("message du tweet {}", message);
        postTweet(message);
        LOGGER.info("tweet envoyé");

    }
}
