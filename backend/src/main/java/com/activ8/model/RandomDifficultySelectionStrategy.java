package com.activ8.model;

import java.util.Random;

public class RandomDifficultySelectionStrategy implements DifficultySelectionStrategy {
    private static final double HARD_THRESHOLD = 0.6;
    private static final double MEDIUM_THRESHOLD = 0.9;
    Random random = new Random();
    
    @Override
    public EDifficulty selectDifficulty() {
        double randomValue = random.nextDouble();
        if (randomValue < HARD_THRESHOLD) return EDifficulty.HARD;
        else if (randomValue < MEDIUM_THRESHOLD) return EDifficulty.MEDIUM;
        else return EDifficulty.EASY;
    }
    
}
