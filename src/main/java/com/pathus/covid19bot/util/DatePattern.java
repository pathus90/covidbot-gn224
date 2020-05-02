package com.pathus.covid19bot.util;

public enum DatePattern {

    DATE_PATTERN_DDMMYYY ("dd-MM-yyyy") ,
    DATE_PATTERN_DDMMYYY_HHMMSS ("yyyy-MM-dd HH:mm:ss") ;

    String format ;

    DatePattern(String format) {
        this.format = format ;
    }

    public String getFormat() {
        return format;
    }
}
