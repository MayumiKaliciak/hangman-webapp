package de.fellowork.mayumi.practice.hangmanwebapp.game.service;

import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.GameState;
import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.GameStateRepository;
import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.HangmanLetterRepository;
import de.fellowork.mayumi.practice.hangmanwebapp.game.service.GuessWordsHandler;
import de.fellowork.mayumi.practice.hangmanwebapp.game.service.HangmanService;
import de.fellowork.mayumi.practice.hangmanwebapp.statistics.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class HangmanServiceTest {

    private GuessWordsHandler guessWordsHandler;
    private StatisticsService statisticsService;
    private GameStateRepository gameStateRepository;
    private HangmanLetterRepository letterRepository;
    private String playerName;
    private HangmanService service;

    @BeforeEach
    void setup(){
        guessWordsHandler = mock(GuessWordsHandler.class);
        statisticsService = mock(StatisticsService.class);
        gameStateRepository = mock(GameStateRepository.class);
        letterRepository = mock(HangmanLetterRepository.class);

        service = new HangmanService(guessWordsHandler, statisticsService, gameStateRepository, letterRepository);
    }

    @Test
    void resetPlayerNameIsNull() {

        fail();

        playerName = "Mayumi";

        when(gameStateRepository.findByPlayerName(playerName)).thenReturn(null);
        GameState gameState = new GameState();
        when(guessWordsHandler.getRandomGuessWord()).thenCallRealMethod();
        when(gameStateRepository.save(gameState)).thenReturn(gameState);

        doNothing().when(statisticsService).setupPlayerStatistics(isA(String.class));
        statisticsService.setupPlayerStatistics(playerName);

        service.reset(playerName);

        InOrder inOrder = inOrder(gameStateRepository, guessWordsHandler, statisticsService);
        inOrder.verify(gameStateRepository).findByPlayerName(playerName);
        inOrder.verify(guessWordsHandler).getRandomGuessWord();
        inOrder.verify(gameStateRepository.save(gameState));
        inOrder.verify(statisticsService, times(1)).setupPlayerStatistics(playerName);

//        when(gameStateRepository.findByPlayerName(playerName)).thenReturn(null);
//        GameState gameState = new GameState();
//        when(guessWordsHandler.getRandomGuessWord()).thenCallRealMethod();
//        when(gameStateRepository.save(gameState)).thenReturn(gameState);
//
//        doNothing().when(statisticsService).setupPlayerStatistics(isA(String.class));
//        statisticsService.setupPlayerStatistics(playerName);
//
//        service.reset(playerName);
//
//        InOrder inOrder = inOrder(gameStateRepository, guessWordsHandler, statisticsService);
//        inOrder.verify(gameStateRepository).findByPlayerName(playerName);
//        inOrder.verify(guessWordsHandler).getRandomGuessWord();
//        inOrder.verify(gameStateRepository.save(gameState));
//        inOrder.verify(statisticsService, times(1)).setupPlayerStatistics(playerName);
    }

    @Test
    void resetPlayerNameIsNotNull() {
        playerName = "Mayumi";

        when(gameStateRepository.findByPlayerName(playerName)).thenReturn(new GameState());

        doNothing().when(statisticsService).setupPlayerStatistics(isA(String.class));
        statisticsService.setupPlayerStatistics(playerName);

        service.reset(playerName);

        InOrder inOrder = inOrder(gameStateRepository, statisticsService);
        inOrder.verify(gameStateRepository).findByPlayerName(playerName);
        inOrder.verify(statisticsService, times(1)).setupPlayerStatistics(playerName);
    }

    @Test
    void getWordToGuess() {
        fail();
    }

    @Test
    void getWrongGuesses() {
        fail();
    }

    @Test
    void getErrorCounter() {
        fail();
    }

    @Test
    void guessLetter() {
        fail();
    }

    @Test
    void lostGame() {
        fail();
    }

    @Test
    void wonGame() {
        fail();
    }
}