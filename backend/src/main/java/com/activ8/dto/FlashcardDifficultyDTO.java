package com.activ8.dto;

import com.activ8.model.EDifficulty;

public record FlashcardDifficultyDTO(String difficulty) {

    public EDifficulty getDifficultyEnum() {
        try {
            return EDifficulty.valueOf(difficulty.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }
}
