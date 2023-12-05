package com.activ8.dto;

import com.activ8.model.EDifficulty;

public record UpdateFlashcardDTO(
    String studySetId,
    String term,
    String definition,
    EDifficulty difficulty
    ) {

    }

