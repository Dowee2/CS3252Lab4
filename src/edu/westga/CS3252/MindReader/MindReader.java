package edu.westga.CS3252.MindReader;

import java.util.HashMap;
import java.util.Scanner;

public class MindReader {

    private final String PREDICT_TEXT = "Guess heads or tails and I'll predict your guess" + System.lineSeparator()
                                        + "What is your guess [h/t]?";

    private Scanner systemInput;
    private int playerScore;
    private int npcScore;

    private HashMap<String,Integer> userTracker;

    public MindReader() {
        this.playerScore = 0;
        this.npcScore = 0;
        this.userTracker = new HashMap<String,Integer>();
        this.systemInput = new Scanner(System.in);
    }

    public MindReader(HashMap<String,Integer> oldKnowledge) {
        this.playerScore = 0;
        this.npcScore = 0;
        this.userTracker = oldKnowledge;
        this.systemInput = new Scanner(System.in);
    }
    private void initalize() {
        System.out.println("Welcome to MindReader" + System.lineSeparator() + this.PREDICT_TEXT);
        String answer = systemInput.nextLine();
        System.out.println(answer);
    }
}
