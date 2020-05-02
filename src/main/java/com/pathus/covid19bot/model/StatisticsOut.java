package com.pathus.covid19bot.model;

public class StatisticsOut {
    private Statistics previousStatistics;
    private Statistics newStatistics;

    public StatisticsOut(Statistics previousStatistics, Statistics newStatistics) {
        this.previousStatistics = previousStatistics;
        this.newStatistics = newStatistics;
    }

    public Statistics getPreviousStatistics() {
        return previousStatistics;
    }

    public void setPreviousStatistics(Statistics previousStatistics) {
        this.previousStatistics = previousStatistics;
    }

    public Statistics getNewStatistics() {
        return newStatistics;
    }

    public void setNewStatistics(Statistics newStatistics) {
        this.newStatistics = newStatistics;
    }
}
