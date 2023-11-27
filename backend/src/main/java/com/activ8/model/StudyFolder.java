package com.activ8.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studyfolders")
public record StudyFolder(
        @Id String id,
        String userId,
        @NotBlank @Size(max = 100) String title,
        @Size(max = 200) String description
) {
    public StudyFolder {
        // Default constructor
    }

    public StudyFolder(String userId, String title, String description) {
        this(null, userId, title, description);
    }
}
