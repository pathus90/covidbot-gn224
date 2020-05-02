package com.pathus.covid19bot.util;

import com.pathus.covid19bot.model.Statistics;
import com.pathus.covid19bot.model.StatisticsOut;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.pathus.covid19bot.util.Constants.*;

public final class Util {

    private Util(){}

    public static String createTweetMessage(StatisticsOut statistics, String hashtag){

        StringBuilder builder = new StringBuilder();

        Statistics newStatistics = statistics.getNewStatistics();
        Statistics previousStatistics = statistics.getPreviousStatistics();
        builder.append(String.format("Toutes les statistiques COVID19 à la date du %s en Guinée  🇬🇳", getNow(DatePattern.DATE_PATTERN_DDMMYYY.getFormat())));
        builder.append("\n");
        builder.append(getMessage("-Infectés", newStatistics.getCases(), previousStatistics!= null ?previousStatistics.getCases():0));
        builder.append(getMessage("-Guéris",newStatistics.getRecovered(), previousStatistics!=null ? previousStatistics.getRecovered(): 0));
        builder.append(getMessage("-Décès",newStatistics.getDeaths(), previousStatistics!=null ? previousStatistics.getDeaths() :0));
        builder.append("\n");
        builder.append(hashtag);

        return builder.toString();

    }

    public static String getNow(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static Statistics parseJsonObject(String jsonStr) {
        JSONObject localObject = new JSONObject(jsonStr).getJSONObject(Constants.LOCAL);
        String now = getNow(DatePattern.DATE_PATTERN_DDMMYYY_HHMMSS.getFormat());
        return new Statistics(localObject.getInt(CONFIRMED), localObject.getInt(DEATHS), localObject.getInt(RECOVERED), now);
    }

    private static String getMessage(String label, int newCase, int oldCase) {
        int diff = oldCase!= 0 ? newCase - oldCase :-1;
        return (diff > 0 ? String.format(label.concat(" : %d(+%d)"),newCase, diff )
                : String.format(label.concat(": %d"),newCase)).concat("\n");
    }
}
