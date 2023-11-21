package activ8testable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/*
 * User - Has a UUID, a username and stores study folders
 */
public class User {
    private UUID uuid;
    private String username;
    private Path userFolder;
    private ArrayList<StudyFolder> studyFolders = new ArrayList<>();


    // Constructor
    public User(UUID uuid, String username, Path folderPath) {
        this.uuid = uuid;
        this.username = username;
        this.userFolder = folderPath;
    }
    
    // Creates a study folder
    public StudyFolder createStudyFolder(String title) {
        StudyFolder folder = new StudyFolder(title);
        studyFolders.add(folder);

        return folder;
    }

    // Add a study set to a study folder
    public void addStudySet(StudyFolder studyFolder, StudySetFlashcard studySetFlashcard) {
        studyFolder.addStudySet(studySetFlashcard);
    }

    // Remove a study set from a study folder
    public void removeStudySet(StudyFolder studyFolder, StudySetFlashcard studySetFlashcard) {
        studyFolder.removeStudySet(studySetFlashcard);
    }

    // Get study folders
    public ArrayList<StudyFolder> getStudyFolders() {
        return studyFolders;
    }

    // Getter for UUID
    public UUID getUuid() {
        return uuid;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username (if needed)
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for userFolder
    public Path getUserFolder() {
        return userFolder;
    }

    
    public boolean isExistingUser(User[] userList) {
        for (User user : userList) {
            if (this.equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username);
    }
}
