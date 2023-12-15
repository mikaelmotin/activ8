package com.activ8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.payload.response.MessageResponse;
import com.activ8.service.StudySessionLogService;
import com.activ8.service.UserDetailsImpl;

/**
 * Controller managing user data-related operations. This controller provides endpoints
 * for retrieving points, username, and study logs of the authenticated user.
 *
 * Endpoints:
 * - GET /api/userData/points: Retrieve the points of the authenticated user.
 * - GET /api/userData/username: Retrieve the username of the authenticated user.
 * - GET /api/userData/studylogs: Retrieve the study logs of the authenticated user.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/userData")
public class UserDataController {
    @Autowired
    StudySessionLogService sessionLogService;

    /**
     * Get points from the User
     * @param userDetails
     * @return ResponseEntity with points (int)
     */
    @GetMapping("/points")
    public ResponseEntity<?> getpoints(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            int points = userDetails.getPoints();
            return ResponseEntity.ok().body(points);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Couldn't retrieve points"));
        }
    }

    /**
     * Get User's username
     * @param userDetails
     * @return ResponseEntity with username (String)
     */
    @GetMapping("/username")
    public ResponseEntity<?> getUsername(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            String username = userDetails.getUsername();
            return ResponseEntity.ok().body(username);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Couldn't retrieve username"));
        }
    }

    /**
     * Get User's studylogs
     * @param userDetails
     * @return ResponseEntity with studylogs 
     */
    @GetMapping("/studylogs")
    public ResponseEntity<?> getStudylogs(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok().body(
                sessionLogService.
                getAllStudySessionLogs(
                    userDetails.getId()
                )
            );
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Couldn't retrieve studylogs"));
        }
    }

}
