package com.activ8.dto;

import com.activ8.model.EDifficulty;

public record CreateFlashcardDTO(
    String term,
    String definition,
    EDifficulty difficulty
) {
    
}
