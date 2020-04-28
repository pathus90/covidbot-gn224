package com.pathus.covid19bot.util;

import com.pathus.covid19bot.model.LocalStats;
import com.pathus.covid19bot.model.Statistics;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TwitterUtil {


    private static final String LOCAL = "local";
    private static final String CONFIRMED = "confirmed";
    private static final String DEATHS = "deaths";
    private static final String RECOVERED = "recovered";

    public static final String DEFAULT_IMG_PATH = "images/COVID19.jpg";

    private TwitterUtil(){}

    public static String createTweetMessage(Statistics statistics, String hashtag){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Toutes les statistiques COVID19 à la date du %s en Guinée  🇬🇳", getNow(Pattern.DATE_PATTERN_DDMMYYY.getFormat())));
        builder.append("\n");
        builder.append(text("-Infectés", statistics.getCases(), statistics.getTodayCases()));
        builder.append(text("-Guéris",statistics.getRecovered(),0));
        builder.append(text("-Décès",statistics.getDeaths(), statistics.getTodayDeaths()));
        builder.append("\n");
        builder.append(hashtag);

        return builder.toString();

    }

    public static String getNow(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static LocalStats parseJsonObject(String jsonStr) {
        JSONObject localObject = new JSONObject(jsonStr).getJSONObject(LOCAL);
        return new LocalStats(localObject.getInt(CONFIRMED), localObject.getInt(DEATHS), localObject.getInt(RECOVERED));
    }

    private static String text(String label, int value, int newCase) {
        return (newCase > 0 ? String.format(label.concat(" : %d(+%d)"),value, newCase )
                : String.format(label.concat(": %d"),value)).concat("\n");
    }

}
