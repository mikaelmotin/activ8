import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserManagerGSON implements UserManager {
    private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(User.class, new UserSerializer())
    .setPrettyPrinting()
    .create();


    public User createUser(String username) throws IOException {
        UUID uuid = UUID.randomUUID();
        Path userFolder = Paths.get("src", "data", "users", uuid.toString());
        Path userDataJSON = Paths.get("src", "data", "users", uuid.toString(), "userData.json");
        
        // Create a folder with the user's UUID as the name
        try {
            Files.createDirectories(userFolder);
            Files.createFile(userDataJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the user
        User newUser = new User(uuid, username, userFolder);
        
        // Save user data
        saveUserData(newUser);

        return newUser;
    }

    public void saveUserData(User user) throws IOException {
        Path userDataFilePath = user.getUserFolder().resolve("userData.json");
        
        try (FileWriter fileWriter = new FileWriter(userDataFilePath.toFile())) {
            gson.toJson(user, fileWriter);
        } catch (IOException e) {
            System.out.println("FAILED SAVING USER DATA: ");
            e.printStackTrace();
        }
    }
    
    public User getUser(UUID uuid) throws IOException {
        Path userFolderPath = Paths.get("src", "data", "users", uuid.toString());
        Path userDataFilePath = userFolderPath.resolve("userData.json");

        if (Files.exists(userDataFilePath)) {
            return getUserFromJsonFile(userDataFilePath);
        }

        return null;
    }

    private static User getUserFromJsonFile(Path jsonFilePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(jsonFilePath, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, User.class);
        }
    }

    public Path getUserFolder(User user) {
        return user.getUserFolder();
    }
    
}