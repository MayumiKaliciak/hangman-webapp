package de.fellowork.hangman.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Jacksonized
@Data
public class GuessModel {

 private String guessedLetter;

 private List<String> guessedLettersList = new ArrayList<>();

}
