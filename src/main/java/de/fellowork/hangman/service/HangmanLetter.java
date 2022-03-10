package de.fellowork.hangman.service;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@RequiredArgsConstructor
public class HangmanLetter {

    private final String letter;
    @Setter
    private boolean correctlyGuessed = false;

}
