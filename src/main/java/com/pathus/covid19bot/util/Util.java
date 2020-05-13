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
        builder.append(String.format("Toutes les statistiques COVID19 à la date du %s en Guinée  🇬🇳", getTodayDate()));
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
        JSONObject localObject = new JSONObject(jsonStr);
        String now = getNow(DatePattern.DATE_PATTERN_DDMMYYY_HHMMSS.getFormat());
        return new Statistics(localObject.getInt(CONFIRMED), localObject.getInt(DEATHS), localObject.getInt(RECOVERED), now);
    }
    private static String getTodayDate() {
        String date = getNow(DatePattern.DATE_PATTERN_DDMMYYY.getFormat());
        String [] dates = date.split("-");
        return String.format("%s %s %s", dates[0],  getMoisChaine(Integer.parseInt(dates[1].substring(1))), dates[2]);
    }

    private static String getMoisChaine(int mois){
        switch(mois){
            case 1 : return "Janvier";
            case 2 : return "Février";
            case 3 : return "Mars";
            case 4 : return "Avril";
            case 5 : return "Mai";
            case 6 : return "Juin";
            case 7 : return "Juillet";
            case 8 : return "Août";
            case 9 : return "Septembre";
            case 10 : return "Octobre";
            case 11 : return "Novembre";
            case 12 :
            default :return"Décembre";
        }
    }

    private static String getMessage(String label, int newCase, int oldCase) {
        int diff = oldCase!= 0 ? newCase - oldCase :-1;
        return (diff > 0 ? String.format(label.concat(" : %d(+%d)"),newCase, diff )
                : String.format(label.concat(": %d"),newCase)).concat("\n");
    }
}
