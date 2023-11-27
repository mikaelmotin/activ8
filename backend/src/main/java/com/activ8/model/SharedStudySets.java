package com.activ8.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sharedstudysets")
public record SharedStudySets(
    String sharedByUserId,
    String sharedWithUserId,
    String studySetId
) {
    
}
