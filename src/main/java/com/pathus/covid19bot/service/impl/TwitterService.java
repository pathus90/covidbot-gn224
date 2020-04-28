package com.pathus.covid19bot.service.impl;

import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.service.ITwitterService;
import com.pathus.covid19bot.util.TwitterUtil;
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
import java.net.MalformedURLException;

@Service
public class TwitterService implements ITwitterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

    @Inject
    private final TwitterTemplate twitterTemplate;

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
        String message = TwitterUtil.createTweetMessage(dataService.getStatisticsByKisalApi(url), hashtag);
        LOGGER.info("message du tweet {}", message);
        postTweet(message, TwitterUtil.DEFAULT_IMG_PATH);
        LOGGER.info("tweet envoyé");
    }


    private Tweet postTweet(String tweetMessage, String imageUrl) {
        TweetData tweetData = new TweetData(tweetMessage);
        try {
            Resource resource = resourceLoader.getResource("classpath:".concat(imageUrl));
            LOGGER.info("Trying to tweet image with url {} content length {}", imageUrl, resource.contentLength());
            tweetData = tweetData.withMedia(resource);
        } catch (MalformedURLException e) {
            LOGGER.error("Malformed url for tweet image: {}", imageUrl);
        } catch (IOException e) {
            LOGGER.error("IOException for tweet image {}\n{}", imageUrl, e);
            }
        return twitterTemplate.timelineOperations().updateStatus(tweetData);
    }

}
