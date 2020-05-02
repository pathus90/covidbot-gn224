package com.pathus.covid19bot.repository;

import com.pathus.covid19bot.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStatisticsRepository extends JpaRepository<Statistics, Long> {
}
