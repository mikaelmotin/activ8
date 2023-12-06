package com.activ8.controller;
import com.activ8.service.*;

import com.activ8.model.Flashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/study")
public class StudySessionController{
    private FlashcardService flashcardService;

    @Autowired
    public StudySessionController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping("/session")
    public String startStudySession(Model model) {
        List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet("222");
        model.addAttribute("flashcards", flashcards);
        return "study-session"; // Return view for study session
    }

}
