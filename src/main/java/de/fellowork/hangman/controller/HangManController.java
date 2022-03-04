package de.fellowork.hangman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HangManController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam String letter){
        return "hallo 1234:" + letter;
    }
}
