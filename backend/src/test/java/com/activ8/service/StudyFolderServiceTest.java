package com.activ8.service;
import com.activ8.model.StudyFolder;
import com.activ8.repository.StudyFolderRepository;
import com.activ8.service.StudyFolderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StudyFolderServiceTest {

    @Mock
    private StudyFolderRepository studyFolderRepository;

    @InjectMocks
    private StudyFolderService studyFolderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveStudyFolder() {
        StudyFolder studyFolderToSave = new StudyFolder("111", "100", "test", "A test folder");
        when(studyFolderRepository.save(studyFolderToSave)).thenReturn(studyFolderToSave);

        StudyFolder savedStudyFolder = studyFolderService.saveStudyFolder(studyFolderToSave);

        assertNotNull(savedStudyFolder);
        assertEquals(studyFolderToSave, savedStudyFolder);
        verify(studyFolderRepository, times(1)).save(studyFolderToSave);
    }

    @Test
    void testGetAllStudyFolders() {
        String userId = "user123";
        List<StudyFolder> studyFolders = new ArrayList<>();
        // Populate studyFolders list with test data

        when(studyFolderRepository.findAllByUserId(userId)).thenReturn(studyFolders);

        List<StudyFolder> retrievedStudyFolders = studyFolderService.getAllStudyFolders(userId);

        assertEquals(studyFolders.size(), retrievedStudyFolders.size());
        verify(studyFolderRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void testGetStudyFolder() {
        String studyFolderId = "123";
        StudyFolder studyFolder = new StudyFolder(studyFolderId, "100", "test", "A test folder");
        when(studyFolderRepository.findById(studyFolderId)).thenReturn(Optional.of(studyFolder));

        Optional<StudyFolder> retrievedStudyFolder = studyFolderService.getStudyFolder(studyFolderId);

        assertTrue(retrievedStudyFolder.isPresent());
        assertEquals(studyFolder, retrievedStudyFolder.get());
        verify(studyFolderRepository, times(1)).findById(studyFolderId);
    }

    @Test
    void testDeleteStudyFolder() {
        String userId = "user123";
        String studyFolderId = "folder456";

        StudyFolder studyFolder = new StudyFolder(studyFolderId, userId, "test", "A test folder");
        when(studyFolderRepository.findById(studyFolderId)).thenReturn(Optional.of(studyFolder));
        when(studyFolderRepository.existsById(studyFolderId)).thenReturn(true);

        boolean deleted = studyFolderService.deleteStudyFolder(userId, studyFolderId);

        assertTrue(deleted);
        verify(studyFolderRepository, times(1)).findById(studyFolderId);
        verify(studyFolderRepository, times(1)).delete(studyFolder);
    }
}
