package com.activ8.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studysessionlogs")
public record StudySessionLog(
    
) 
{
    // Type of StudySession
    // Number of cards went through
    // Stats for Number of difficulty cards
    // Date
    // Time spent studying

} 




