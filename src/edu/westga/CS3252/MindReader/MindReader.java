package edu.westga.CS3252.MindReader;

import java.util.HashMap;
import java.util.Scanner;

import edu.westga.CS3252.MindReader.Utils.Utils;

public class MindReader {

    private final String PREDICT_TEXT = "Guess heads or tails and I'll predict your guess" + System.lineSeparator()
                                        + "What is your guess [h/t]?";

    private Scanner systemInput;
    private int playerScore;
    private int npcScore;

    private GuessQueue playerGuesses;

    private HashMap<GuessQueue,Integer> playerTracker;

    public MindReader() {
        this.playerScore = 0;
        this.npcScore = 0;
        this.playerTracker = new HashMap<GuessQueue,Integer>();
        this.systemInput = new Scanner(System.in);
        this.playerGuesses = new GuessQueue();
        this.startGame();
    }

    public MindReader(HashMap<GuessQueue,Integer> oldKnowledge) {
        this.playerScore = 0;
        this.npcScore = 0;
        this.playerTracker = oldKnowledge;
        this.systemInput = new Scanner(System.in);
        this.playerGuesses = new GuessQueue();
        
        this.startGame();
    }
    private void startGame() {
        System.out.println("Welcome to MindReader" + System.lineSeparator() + this.PREDICT_TEXT);
        this.handlePlayerGuess(systemInput.nextLine());
    }

   

    private void handlePlayerGuess(String guess) {
        guess = guess.trim();
        guess = guess.toLowerCase();
        this.handleInvalidInput(guess);
        String npcGuess = this.makeGuess();

        if (npcGuess.equals(guess)) {
            this.npcScore++;
            System.out.println("I guessed right!" + System.lineSeparator() + 
            "Your score: " + this.playerScore + System.lineSeparator() + "My score: " + this.npcScore);
        } else {
            this.playerScore++;
            System.out.println("I guessed wrong!" + System.lineSeparator() + 
            "Your score: " + this.playerScore + System.lineSeparator() + "My score: " + this.npcScore);
        }

        if(this.playerGuesses.size() < 4) {
            this.playerGuesses.add(guess);
        } else {
            if (this.playerTracker.containsKey(this.playerGuesses)) {
                this.calculateGuessValue(guess);
                this.playerGuesses.add(guess);
            } else {
                this.playerTracker.put(this.playerGuesses.clone(), 0);
                this.playerGuesses.add(guess);
            }
        }
        this.handleRoundEnd();
    }

    private String makeGuess() {
        int guesswieght = 0;
        String guess = "";
        
        if (this.playerTracker.get(playerGuesses) == null) {
            return Utils.randomGuess();
        } else {
            guesswieght = this.playerTracker.get(playerGuesses);
        }

        if (guesswieght < 0) {
            guess = "t";
        } else if (guesswieght > 0) {
            guess = "h";
        } else {
            guess = Utils.randomGuess();
        }
        return guess;
    }

    private void handleInvalidInput(String guess) {
        if (!(guess.equals("h") || guess.equals("t"))) {
            System.out.println("Invalid input. Please enter h or t");
            this.handlePlayerGuess(systemInput.nextLine());
        }
    }

    private void calculateGuessValue(String guess) {
        if (guess.equals("h") ) {
            this.playerTracker.put(this.playerGuesses, this.playerTracker.get(this.playerGuesses) + 1);
        } else {
            this.playerTracker.put(this.playerGuesses, this.playerTracker.get(this.playerGuesses) - 1);
        }
    }

    private void handleRoundEnd() {
        if (this.playerScore < 25 && this.npcScore < 25) {
            System.out.println(this.PREDICT_TEXT);
            this.handlePlayerGuess(systemInput.nextLine());
        } else {
            this.handleGameEnd();
        }
            
    }

    private void handleGameEnd() {
        System.out.println("Would you like to play again? [y/n]");
        String input = systemInput.nextLine();
        if (input.toLowerCase().equals("y")) {
            this.playerScore = 0;
            this.npcScore = 0;
            this.startGame();

        } else {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }
}
