package com.pathus.covid19bot.service;

import com.pathus.covid19bot.model.StatisticsOut;

public interface IDataService {

    StatisticsOut getStatistics(String kisalUrl);
}
