package de.fellowork.mayumi.practice.hangmanwebapp.game.controller;

import de.fellowork.mayumi.practice.hangmanwebapp.game.service.HangmanService;
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
    public String hangMan( Model model) {
        model.addAttribute("guessModel", new GuessModel());
        return "index";
    }
    @GetMapping("/")
    public String index( Model model) {
        model.addAttribute("guessModel", new GuessModel());
        return "index";
    }

    @PostMapping("/start-hangman-game")
    public String startHangMan(@ModelAttribute GuessModel guessModel, Model model) {
        String playerName = guessModel.getPlayerName();
        hangmanService.reset(playerName);

        List<String> wordToGuess = hangmanService.getWordToGuess(playerName);

        setModelAttributes(model, wordToGuess, guessModel);

        return "hangman";
    }



    @PostMapping("/hangman")
    public String wordGuess(@ModelAttribute GuessModel guessModel, Model model) throws InterruptedException {

        String playerName = guessModel.getPlayerName();
        List<String> wordToGuess = hangmanService.guessLetter(guessModel.getGuessedLetter(), playerName);

        if(hangmanService.lostGame(playerName)){
            return "lostpage";
        }
        if(hangmanService.wonGame(playerName)){
            return "winpage";
        }
        guessModel.setGuessedLetter(null);
        setModelAttributes(model, wordToGuess, guessModel);
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

    private void setModelAttributes(Model model, List<String> wordToGuess, GuessModel guessModel) {
        model.addAttribute("wordToGuess", wordToGuess);

        List<String> wronglyGuessedLetters = hangmanService.getWrongGuesses(guessModel.getPlayerName());
        model.addAttribute("wronglyGuessedLetters", wronglyGuessedLetters);

        int errorCounter = hangmanService.getErrorCounter(guessModel.getPlayerName());
        model.addAttribute("errorCounter", errorCounter);
        model.addAttribute("imageUrl", "pictures/" + errorCounter +".jpg");

        model.addAttribute("guessModel",guessModel);
    }

}
