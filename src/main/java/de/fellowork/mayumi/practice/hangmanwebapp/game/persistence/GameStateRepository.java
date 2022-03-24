package de.fellowork.mayumi.practice.hangmanwebapp.game.persistence;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface GameStateRepository extends CrudRepository<GameState, Long> {
    GameState findByPlayerName(String playerName);
    void deleteByPlayerName(String playerName);
}
