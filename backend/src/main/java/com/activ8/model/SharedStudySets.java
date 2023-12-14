package com.activ8.model;

import org.springframework.data.mongodb.core.mapping.Document;


// Did not have time to implement this
@Document(collection = "sharedstudysets")
public record SharedStudySets(
    String sharedByUserId,
    String sharedWithUserId,
    String studySetId
) {
    
}
