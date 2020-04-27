package com.pathus.covid19bot.service;

import com.pathus.covid19bot.model.Statistics;

public interface IDataService {
    Statistics getStatistics(String url);
}
