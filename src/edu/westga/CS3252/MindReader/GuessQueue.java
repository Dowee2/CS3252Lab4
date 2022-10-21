package edu.westga.CS3252.MindReader;

import java.util.LinkedList;

public class GuessQueue extends LinkedList<String> {
    private static final long serialVersionUID = 1L;
    private final int MAX_SIZE = 4;

    public GuessQueue() {
        super();
    }

    public boolean add(String guess) {
        if (this.size() == this.MAX_SIZE) {
            this.remove();
        }
        return super.add(guess);
        
    }

    public boolean equals(GuessQueue other) {
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    public GuessQueue clone() {
        GuessQueue clone = new GuessQueue();
        for (int i = 0; i < this.size(); i++) {
            clone.add(this.get(i));
        }
        return clone;
    }
}

