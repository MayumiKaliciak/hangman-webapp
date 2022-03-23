package de.fellowork.hangman.game.persistence;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface HangmanLetterRepository extends CrudRepository<HangmanLetter, Long> {

}
