package de.fellowork.hangman.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Jacksonized
@Data
@Component
public class WordToGuessModel {

    List<String> letterList;

}
