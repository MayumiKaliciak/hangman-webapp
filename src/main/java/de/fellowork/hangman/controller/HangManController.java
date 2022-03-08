package de.fellowork.hangman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HangManController {

	@PostMapping("/hangman")
	public String wordGuess(@ModelAttribute Guess guess, Model model) {
		log.info("Guess: " + guess);
		model.addAttribute("guess", new Guess());
		return "hangman";
	}

	@GetMapping("/hangman")
	public String hangMan(Model model) {
		model.addAttribute("guess", new Guess());
		return "hangman";
	}

	@PostMapping("/start-hangman-game")
	public String startHangMan(Model model) {
		// init new game
		return "redirect:/hangman";
	}

}
