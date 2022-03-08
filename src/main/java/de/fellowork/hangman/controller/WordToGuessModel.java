package de.fellowork.hangman.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Data
public class WordToGuessModel {

    List<String> letterList;

}
