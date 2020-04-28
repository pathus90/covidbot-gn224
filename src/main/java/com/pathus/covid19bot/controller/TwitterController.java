package com.pathus.covid19bot.controller;

import com.pathus.covid19bot.service.IDataService;
import com.pathus.covid19bot.util.Pattern;
import com.pathus.covid19bot.util.TwitterUtil;
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

        model.addAttribute("statistics", dataService.getStatisticsByKisalApi(url));
        model.addAttribute("date", TwitterUtil.getNow(Pattern.DATE_PATTERN_DDMMYYY_HHMMSS.getFormat()));

        return "welcome";
    }
}
