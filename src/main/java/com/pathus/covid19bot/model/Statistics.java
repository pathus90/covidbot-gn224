package com.pathus.covid19bot.model;

import javax.persistence.*;

@Entity
@Table(name = "STATS")
public class Statistics {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "CASES", nullable = false)
    private int cases;

    @Column(name = "DEATHS", nullable = false)
    private int deaths;

    @Column(name = "RECOVERED", nullable = false)
    private int recovered;

    @Column(name = "UPDATE_TIME", nullable = false)
    private String updatedTime;

    public Statistics() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Statistics(int cases, int deaths, int recovered, String updatedTime) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.updatedTime = updatedTime;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
