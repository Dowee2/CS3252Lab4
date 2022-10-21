package edu.westga.CS3252.MindReader.Utils;

public class Utils {
    
    public static String randomGuess() {
        double random = Math.random();
        if (random > 0.5) {
            return "h";
        } else {
            return "t";
        }
    }
}
