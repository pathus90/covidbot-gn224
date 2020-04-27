package com.pathus.covid19bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfig {

    @Value("${spring.social.twitter.appId}")
    private String consumerKey;

    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    private TwitterTemplate template;

    @Bean
    TwitterTemplate getTwtTemplate(){
        template = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        template.getRestTemplate().getInterceptors().add (0, new ContentTypeInterceptor());
        return template;
    }
}