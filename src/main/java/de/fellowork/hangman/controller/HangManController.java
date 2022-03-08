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

	@PostMapping("/hangman")
	public String wordGuess(@ModelAttribute GuessModel guessModel, Model model) {
		log.info("Guess: " + guessModel);
		model.addAttribute("guessModel", new GuessModel());
		return "hangman";
	}

	@GetMapping("/hangman")
	public String hangMan(Model model) {

		WordToGuessModel wordToGuessModel = new WordToGuessModel();
		wordToGuessModel.setWordToGuess(this.currentWord);
		model.addAttribute("wordToGuess", wordToGuessModel);
		model.addAttribute("guessModel", new GuessModel());

		return "hangman";
	}

	@PostMapping("/start-hangman-game")
	public String startHangMan(Model model) {

		this.currentWord = List.of("T","E","L","E","K","O","M");

		return "redirect:/hangman";
	}

}
