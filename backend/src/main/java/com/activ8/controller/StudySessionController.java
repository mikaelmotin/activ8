package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import com.activ8.model.StudySessionLog;
import com.activ8.service.StudySessionService;
import com.activ8.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysessions")
public class StudySessionController {

    @Autowired
    private StudySessionService studySessionService;

    // GET REQUESTS

    @GetMapping
    public ResponseEntity<List<StudySessionLog>> getStudySessions(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StudySessionLog> studySessions = studySessionService.getAllStudySessions(userDetails.getId());
        return ResponseEntity.ok(studySessions);
    }

    @GetMapping("/{studySessionId}")
    public ResponseEntity<?> getStudySessionById(@PathVariable String studySessionId) {
        return studySessionService.getStudySessionById(studySessionId)
                .map(studySession -> ResponseEntity.ok().body(studySession))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST REQUESTS

    /**
     * Starts a new StudySession
     * 
     * @param userDetails          User details of the authenticated user
     * @return The started StudySession
     */
    @PostMapping("/start-free-roam")
    public ResponseEntity<?> startStudySession(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            studySessionService.startFreeRoamStudySession(userDetails.getId());
        } catch (Exception e) {
            return ResponseEntity.ok("startSession failed");
        }


        return ResponseEntity.ok("startSession success");
    }

}
