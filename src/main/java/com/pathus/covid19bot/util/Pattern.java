package com.pathus.covid19bot.util;

public enum Pattern {

    DATE_PATTERN_DDMMYYY ("dd-MM-yyyy") ,
    DATE_PATTERN_DDMMYYY_HHMMSS ("yyyy-MM-dd HH:mm:ssZ") ;
    String format ;

    Pattern(String format) {
        this.format = format ;
    }

    public String getFormat() {
        return format;
    }
}
