package com.activ8.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "studysets")
public record StudySet(
        @Id
        String id,
        String ownerId,
        String studyFolderId,
        @NotBlank @Size(max = 100) String title,
        @Size(max = 200) String description
) {
    public StudySet {
        // Default constructor
    }

    public StudySet(String ownerId, String studyFolderId, String title, String description) {
        this(null, ownerId, studyFolderId, title, description);
    }
}
