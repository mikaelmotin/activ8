package activ8testable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public interface UserManager {
    User createUser(String username) throws IOException;
    User getUser(UUID uuid) throws IOException;
    void saveUserData(User user) throws IOException;
    Path getUserFolder(User user);
}
