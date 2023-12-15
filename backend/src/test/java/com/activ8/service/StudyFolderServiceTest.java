package com.activ8.service;
import com.activ8.model.StudyFolder;
import com.activ8.model.StudySet;
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
    @Mock
    private StudySetService studySetService;

    @InjectMocks
    private StudyFolderService studyFolderService;
    private String userId = "userId";
    private String studyFolderId = "folderId";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Test to save a study folder and verify the saved study folder matches the expected one.
     */
    @Test
    void testSaveStudyFolder() {
        StudyFolder studyFolderToSave = new StudyFolder(studyFolderId, userId, "test", "A test folder");
        when(studyFolderRepository.save(studyFolderToSave)).thenReturn(studyFolderToSave);

        StudyFolder savedStudyFolder = studyFolderService.saveStudyFolder(studyFolderToSave);

        assertNotNull(savedStudyFolder);
        assertEquals(studyFolderToSave, savedStudyFolder);
    }
    /**
     * Test to retrieve all study folders and verify the count matches the expected number.
     */
    @Test
    void testGetAllStudyFolders() {
        List<StudyFolder> studyFolders = new ArrayList<>();
        StudyFolder studyFolder = new StudyFolder(studyFolderId, userId, "test", "A test folder");
        studyFolderService.saveStudyFolder(studyFolder);


        when(studyFolderRepository.findAllByUserId(userId)).thenReturn(studyFolders);

        List<StudyFolder> retrievedStudyFolders = studyFolderService.getAllStudyFolders(userId);

        assertEquals(studyFolders.size(), retrievedStudyFolders.size());
    }
    /**
     * Test to retrieve a study folder by ID and ensure it matches the expected study folder.
     */
    @Test
    void testGetStudyFolder() {
        StudyFolder studyFolder = new StudyFolder(studyFolderId, userId, "test", "A test folder");
        when(studyFolderRepository.findById(studyFolderId)).thenReturn(Optional.of(studyFolder));

        Optional<StudyFolder> retrievedStudyFolder = studyFolderService.getStudyFolder(studyFolderId);

        assertTrue(retrievedStudyFolder.isPresent());
        assertEquals(studyFolder, retrievedStudyFolder.get());
    }
    /**
     * Test to delete a study folder and verify its deletion based on certain conditions.
     */
    @Test
    void testDeleteStudyFolder() {

        StudyFolder studyFolder = new StudyFolder(studyFolderId, userId, "test", "A test folder");
        when(studyFolderRepository.findById(studyFolderId)).thenReturn(Optional.of(studyFolder));

        List<StudySet> studySets = new ArrayList<>();
        StudySet testStudySet = new StudySet(userId,studyFolderId,"test","A test set");
        studySets.add(testStudySet);

        when(studySetService.getAllStudySets(studyFolderId)).thenReturn(studySets);
        when(studySetService.deleteStudySet(userId, testStudySet.id())).thenReturn(true); // Mock the deleteStudySet method

        boolean deleted = studyFolderService.deleteStudyFolder(userId, studyFolderId);

        assertTrue(deleted);

    }
}
