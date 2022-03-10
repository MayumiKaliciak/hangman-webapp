package de.fellowork.hangman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("wordToGuessModel")
    WordToGuessModel wordToGuessModel;

    private List<String> currentWord;
    private List<String> correctlyGuessedLetters = new ArrayList<>();
    private List<String> wronglyGuessedLetters = new ArrayList<>();
    private int counter = 0;


    @GetMapping("/hangman")
    public String hangMan(Model model) {

        return "index";
    }
    
    @PostMapping("/start-hangman-game")
    public String startHangMan(Model model) {
        reset();

        this.currentWord = List.of("T", "E", "L", "E", "K", "O", "M");
        setWordModel(model);
        
        return "hangman";
    }

    @PostMapping("/hangman")
    public String wordGuess(@ModelAttribute GuessModel guessModel, Model model) {
        log.info("Guess: " + guessModel);

        if(letterIsPartOfWord(guessModel)){
            putLetterIntoCorrectLettersList(guessModel);
            setWordModel(model);
            setAttributesForErrors(model);
        } else {
            setupWronglyGuessedWordsList(guessModel, model);
            setWordModel(model);
            countErrors(model);
        }

        if (passedErrorMargin()){

            return "lostpage";
        }
        if (allCorrectLettersFound()){

            return "winpage";
        }
        return "hangman";
    }

    @GetMapping("/lostpage")
    public String lostGameEnd(Model model) {

        return "index";
    }

    @GetMapping("/winpage")
    public String wonGameEnd(Model model) {

        return "index";
    }

    private void setAttributesForErrors(Model model) {
        model.addAttribute("guessedLetters", wronglyGuessedLetters);
        model.addAttribute("errorCounter", counter);
    }

    private void putLetterIntoCorrectLettersList(GuessModel guessModel) {
        correctlyGuessedLetters.add(guessModel.getGuessedLetter());
    }

    private boolean letterIsPartOfWord(GuessModel guessModel) {
        return currentWord.contains(guessModel.getGuessedLetter());
    }

    private void countErrors(Model model) {
        counter = wronglyGuessedLetters.size();
        model.addAttribute("errorCounter", counter);
    }

    private void setupWronglyGuessedWordsList(GuessModel guessModel, Model model) {
        wronglyGuessedLetters.add(guessModel.getGuessedLetter());
        model.addAttribute("guessedLetters", wronglyGuessedLetters);
    }

    private void setWordModel(Model model) {
//        WordToGuessModel wordToGuessModel = new WordToGuessModel();
        wordToGuessModel.setLetterList(getFilteredLetterList());
        model.addAttribute("wordToGuess", wordToGuessModel);
        model.addAttribute("guessModel", new GuessModel());
    }

	private List<String> getFilteredLetterList() {
		return this.currentWord.stream().map(this::mapFiltered).toList();
	}

	private String mapFiltered(String inputLetter) {
		if (correctlyGuessedLetters.contains(inputLetter)) {
			return inputLetter;
		}
		return "-";
	}

    private boolean passedErrorMargin() {
        return counter >= 5;
    }

    private boolean allCorrectLettersFound() {
        return correctlyGuessedLetters.size() == (currentWord.size() - 1);
    }

    private void reset(){
        this.currentWord = new ArrayList<>();
        this.correctlyGuessedLetters = new ArrayList<>();
        this.wronglyGuessedLetters = new ArrayList<>();
        this.counter = 0;
    }
}
