package com.activ8.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "studysets")
public record StudySet(
        @Id String id,
        String studyFolderId,
        List<String> sharedWithUserIds,
        @NotBlank @Size(max = 100) String title,
        @Size(max = 200) String description
) {
    public StudySet {
        // Default constructor
    }

    public StudySet(String studyFolderId, String title, String description) {
        this(null, studyFolderId, null, title, description);
    }
}