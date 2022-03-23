package de.fellowork.hangman.game.persistence;

import de.fellowork.hangman.statistics.persistence.Statistics;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
public interface GameStateRepository extends CrudRepository<GameState, Long> {
    GameState findByPlayerName(String playerName);
    void deleteByPlayerName(String playerName);
}
