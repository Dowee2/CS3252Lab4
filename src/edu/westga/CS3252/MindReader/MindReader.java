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

        this.initalize();
    }

    public MindReader(HashMap<GuessQueue,Integer> oldKnowledge) {
        this.playerScore = 0;
        this.npcScore = 0;
        this.playerTracker = oldKnowledge;
        this.systemInput = new Scanner(System.in);
        
        this.initalize();
    }
    private void initalize() {
        System.out.println("Welcome to MindReader" + System.lineSeparator() + this.PREDICT_TEXT);
        this.handlePlayerGuess(systemInput.nextLine());
    }

    private String makeGuess() {
        int guesswieght = this.playerTracker.get(this.playerGuesses);
        String guess = "";

        if (guesswieght < 0) {
            guess = "t";
        } else if (guesswieght > 0) {
            guess = "h";
        } else {
            guess = Utils.randomGuess();
        }
        return guess;
    }

    private void handlePlayerGuess(String guess) {
        this.handleInvalidInput(guess);

        String npcGuess = this.makeGuess();
        if (npcGuess.equals(guess)) {
            this.npcScore++;
            System.out.println("You guessed right! You have " + this.playerScore + " points.");
        } else {
            this.playerScore++;
            System.out.println("You guessed wrong! I have " + this.npcScore + " points.");
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
    }

    private void handleInvalidInput(String guess) {
        guess = guess.trim();
        guess = guess.toLowerCase();
        if (guess.equals("h") || guess.equals("t")) {
            this.handlePlayerGuess(guess);
        } else {
            System.out.println("Invalid input. Please enter h or t");
            this.handlePlayerGuess(systemInput.nextLine());
        }
    }

    private void calculateGuessValue(String guess) {
        if (guess.toLowerCase() == "h") {
            this.playerTracker.put(this.playerGuesses, this.playerTracker.get(this.playerGuesses) + 1);
        } else {
            this.playerTracker.put(this.playerGuesses, this.playerTracker.get(this.playerGuesses) - 1);
        }
    }



    

}
