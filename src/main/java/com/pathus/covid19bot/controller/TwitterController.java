package com.pathus.covid19bot.controller;

import com.pathus.covid19bot.model.Statistics;
import com.pathus.covid19bot.model.StatisticsOut;
import com.pathus.covid19bot.service.IDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class TwitterController {

    @Value("${guinea.data.url}")
    private String url;

    @Inject
    private final IDataService dataService;

    public TwitterController(IDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String main(Model model) {
        StatisticsOut statistics = dataService.getStatistics(url);
        Statistics newStats = statistics.getNewStatistics();
        Statistics oldStats = statistics.getPreviousStatistics();

        model.addAttribute("statistics", statistics.getNewStatistics());
        model.addAttribute("todayCases", newStats.getCases()-oldStats.getCases());
        model.addAttribute("todayDeaths", newStats.getDeaths()-oldStats.getDeaths());
        model.addAttribute("todayRecovered", newStats.getRecovered()-oldStats.getRecovered());

        return "welcome";
    }
}
