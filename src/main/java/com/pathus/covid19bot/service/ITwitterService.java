package com.pathus.covid19bot.service;

import org.springframework.social.twitter.api.Tweet;

public interface ITwitterService {

    Tweet postTweetWithImage(String message, String path);

    Tweet postTweet(String message);

    void getHomeTimeline();

    void processPostTweet();
}
