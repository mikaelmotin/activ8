package com.activ8.dto;

import com.activ8.repository.StudySetRepository;
import com.activ8.service.FlashcardService;

public record CreateStudySessionDTO(StudySetRepository studySetRepository) {

}
