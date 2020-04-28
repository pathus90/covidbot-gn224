package com.pathus.covid19bot.service;

import com.pathus.covid19bot.model.LocalStats;
import com.pathus.covid19bot.model.Statistics;

public interface IDataService {
    Statistics getStatistics(String url);

    LocalStats getStatisticsByKisalApi(String kisalUrl);
}
