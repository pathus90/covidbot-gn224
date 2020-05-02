package com.pathus.covid19bot.service.impl;

import com.pathus.covid19bot.model.Statistics;
import com.pathus.covid19bot.model.StatisticsOut;
import com.pathus.covid19bot.repository.IStatisticsRepository;
import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.util.Util;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@Service
public class DataService implements IDataService {

    private final RestTemplate restTemplate;
    private final IStatisticsRepository statisticsRepository;

    @Inject
    public DataService(RestTemplateBuilder restTemplateBuilder, IStatisticsRepository statisticsRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public StatisticsOut getStatistics(String url){
        Statistics newStatistics = Util.parseJsonObject(restTemplate.getForObject(url, String.class));
        Statistics oldStatistics = statisticsRepository.findAll().stream().findFirst().orElse(null);
        return new StatisticsOut(oldStatistics,newStatistics);
    }
}
