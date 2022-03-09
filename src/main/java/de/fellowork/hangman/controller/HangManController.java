package de.fellowork.hangman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class HangManController {

    private List<String> currentWord;
    private List<String> guessedLetters = new ArrayList<>();

    @PostMapping("/hangman")
    public String wordGuess(@ModelAttribute GuessModel guessModel, Model model) {
        log.info("Guess: " + guessModel);


        if(!currentWord.contains(guessModel.getGuessedLetter())){
            guessedLetters.add(guessModel.getGuessedLetter());
            model.addAttribute("guessedLetters", guessedLetters);
        }

        setWordModel(model);

        return "hangman";
    }


    @GetMapping("/hangman")
    public String hangMan(Model model) {

        return "index";
    }

    @PostMapping("/start-hangman-game")
    public String startHangMan(Model model) {

        this.currentWord = List.of("T", "E", "L", "E", "K", "O", "M");
        setWordModel(model);
        return "hangman";
    }

    private void setWordModel(Model model) {
        WordToGuessModel wordToGuessModel = new WordToGuessModel();
        wordToGuessModel.setLetterList(getFilteredLetterList());
        model.addAttribute("wordToGuess", wordToGuessModel);
        model.addAttribute("guessModel", new GuessModel());
    }

	private List<String> getFilteredLetterList() {
		return this.currentWord.stream().map(this::mapFiltered).toList();
	}

	private String mapFiltered(String inputLetter) {
		if (guessedLetters.contains(inputLetter)) {
			return inputLetter;
		}
		return "-";
	}
}
