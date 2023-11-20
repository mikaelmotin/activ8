package activ8testable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class UserManagerMongoDB implements UserManager{

    @Override
    public User createUser(String username) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public User getUser(UUID uuid) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public void saveUserData(User user) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUserData'");
    }

    @Override
    public Path getUserFolder(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserFolder'");
    }
    
}
