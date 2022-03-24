package de.fellowork.mayumi.practice.hangmanwebapp.statistics.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatisticsRepository extends CrudRepository<Statistics, Long> {
Optional<Statistics> findByPlayerName(String playerName);
}
