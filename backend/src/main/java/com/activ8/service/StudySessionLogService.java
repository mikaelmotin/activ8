package com.activ8.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activ8.model.StudySessionLog;
import com.activ8.repository.StudySessionLogRepository;

/**
 * Used for DB applications  
 */
@Service
public class StudySessionLogService {
    @Autowired
    StudySessionLogRepository studySessionLogRepository;

        // Database related operations:
    public List<StudySessionLog> getAllStudySessionLogs(String userId) {
        List<StudySessionLog> logs = studySessionLogRepository.findAllByUserId(userId);

        return logs;
    }

    public Optional<StudySessionLog> getStudySessionLogById(String id) {
        return studySessionLogRepository.findById(id);
    }

    public StudySessionLog saveStudySessionLog(StudySessionLog studySessionLogToSave) {
        StudySessionLog savedStudySessionLog = studySessionLogRepository.save(studySessionLogToSave);
        return savedStudySessionLog;
    }

}
