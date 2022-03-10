package de.fellowork.hangman.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Jacksonized
@Data
public class GuessModel {

 private String guessedLetter;

}
