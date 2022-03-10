package de.fellowork.hangman.controller;

import de.fellowork.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HangManController {

    private final HangmanService hangmanService;

    @GetMapping("/hangman")
    public String hangMan() {

        return "index";
    }
    
    @PostMapping("/start-hangman-game")
    public String startHangMan(Model model) {

        hangmanService.reset();

        List<String> wordToGuess = hangmanService.getWordToGuess();
        setModelAttributes(model, wordToGuess);

        return "hangman";
    }

    @PostMapping("/hangman")
    public String wordGuess(@ModelAttribute GuessModel guessModel, Model model) {

        List<String> wordToGuess = hangmanService.guessLetter(guessModel.getGuessedLetter());

        if(hangmanService.lostGame()){
            return "lostpage";
        }
        if(hangmanService.wonGame()){
            return "winpage";
        }
        setModelAttributes(model, wordToGuess);
        return "hangman";
    }

    @GetMapping("/lostpage")
    public String lostGameEnd() {

        return "index";
    }

    @GetMapping("/winpage")
    public String wonGameEnd() {

        return "index";
    }

    private void setModelAttributes(Model model, List<String> wordToGuess) {
        model.addAttribute("wordToGuess", wordToGuess);

        List<String> wronglyGuessedLetters = hangmanService.getWrongGuesses();
        model.addAttribute("wronglyGuessedLetters", wronglyGuessedLetters);

        int errorCounter = hangmanService.getErrorCounter();
        model.addAttribute("errorCounter", errorCounter);

        model.addAttribute("guessModel", new GuessModel());
    }

}
