package com.pathus.covid19bot.service.impl;

import com.pathus.covid19bot.model.LocalStats;
import com.pathus.covid19bot.model.Statistics;
import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.util.TwitterUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@Service
public class DataService implements IDataService {

    private final RestTemplate restTemplate;

    @Inject
    public DataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Statistics getStatistics(String url) {
        return this.restTemplate.getForObject(url, Statistics.class);
    }

    @Override
    public LocalStats getStatisticsByKisalApi(String url){
        return TwitterUtil.parseJsonObject(restTemplate.getForObject(url, String.class));
    }
}
