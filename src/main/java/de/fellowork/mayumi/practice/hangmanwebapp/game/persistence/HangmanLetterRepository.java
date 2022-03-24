package de.fellowork.mayumi.practice.hangmanwebapp.game.persistence;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface HangmanLetterRepository extends CrudRepository<HangmanLetter, Long> {

}
