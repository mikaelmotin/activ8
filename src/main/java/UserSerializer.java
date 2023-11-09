import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


public class UserSerializer implements JsonSerializer<User>, JsonDeserializer<User> {
    @Override
public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
    if (typeOfSrc == User.class) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", user.getUuid().toString());
        jsonObject.addProperty("username", user.getUsername());
        jsonObject.addProperty("userFolder", user.getUserFolder().toString());

        // Serialize StudyFolders
        JsonArray studyFoldersArray = new JsonArray();
        for (StudyFolder studyFolder : user.getStudyFolders()) {
            JsonObject studyFolderObject = new JsonObject();
            studyFolderObject.addProperty("title", studyFolder.getTitle());
            // Add more properties if needed

            // Serialize StudySets
            JsonArray studySetsArray = new JsonArray();
            for (StudySet studySet : studyFolder.getStudysets()) {
                JsonObject studySetObject = new JsonObject();
                studySetObject.addProperty("title", studySet.getTitle());
                // Add more properties if needed

                // Serialize Flashcards
                JsonArray flashcardsArray = new JsonArray();
                for (Flashcard flashcard : studySet.getFlashcardsList()) {
                    JsonObject flashcardObject = new JsonObject();
                    flashcardObject.addProperty("term", flashcard.getTerm());
                    flashcardObject.addProperty("definition", flashcard.getDefintion());
                    flashcardsArray.add(flashcardObject);
                }
                studySetObject.add("flashcards", flashcardsArray);

                studySetsArray.add(studySetObject);
            }
            studyFolderObject.add("studySets", studySetsArray);

            studyFoldersArray.add(studyFolderObject);
        }

        jsonObject.add("studyFolders", studyFoldersArray);

        return jsonObject;
    } else {
        return null;
    }
}


    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        UUID uuid = UUID.fromString(jsonObject.get("uuid").getAsString());
        String username = jsonObject.get("username").getAsString();
        Path userFolder = Paths.get(jsonObject.get("userFolder").getAsString());
        // Create a new User object with the deserialized data
        return new User(uuid, username, userFolder);
    }
}
