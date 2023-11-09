import java.io.IOException;
import java.util.UUID;

public class App {
    public static void main(String[] args) throws IOException {
        UserManagerGSON userManager = new UserManagerGSON();

        User user =  userManager.createUser("Michelangelito");
        
        Flashcard flashcard = new Flashcard("Äpple", "Apple");
        Flashcard flashcard2 = new Flashcard("Äpple", "Manzana");
        Flashcard flashcard3 = new Flashcard("Äpple", "Apfel");
        
        StudySet set = new StudySet("English");
        StudySet set2 = new StudySet("Spanish");
        StudySet set3 = new StudySet("German");
        set.addFlashcard(flashcard);
        set2.addFlashcard(flashcard2);
        set3.addFlashcard(flashcard3);

        StudyFolder folder = user.createStudyFolder("Glossary"); 
        user.addStudySet(folder, set);
        user.addStudySet(folder, set2);

        userManager.saveUserData(user);
        
        
        //UserManager.getUser(user.getUuid()).addStudySet(folder, set3);;
        // UUID apa = UUID.fromString("e1fd6696-59bb-4c0e-8f6b-4cd902db3238");
        // User user = userManager.getUser(apa);
        // System.out.println(user.getUsername());
        //UserManager.saveUserData(user);

        for(StudyFolder f: user.getStudyFolders()) {
            for(StudySet s: f.getStudysets()) {
                System.out.println(s.getTitle());
            }
        }

































    //     Flashcard card1 = new Flashcard("apa", "monkey");
    //     Flashcard card2 = new Flashcard("hund", "dawg");
    //     Flashcard card3 = new Flashcard("katt", "cat");
    //     Flashcard card4 = new Flashcard("groda", "frog");

    //     StudySet studySet = new StudySet("Swedish-English glossary");
    //     studySet.addFlashcard(card1);
    //     studySet.addFlashcard(card2);
    //     studySet.addFlashcard(card3);
    //     studySet.addFlashcard(card4);

    //     studySet.editFlashcard(card4, "321123", "423423");

    //     StudySet studySet2 = new StudySet("St2");
    //     studySet2.addFlashcard(card1);
    //     studySet2.addFlashcard(card2);
    //     studySet2.addFlashcard(card3);
    //     studySet2.addFlashcard(card4);

    //     User user = new User("TestUser");
    //     user.addStudySet(studySet);
    //     user.addStudySet(studySet2);



    //     // Test saving a user
    //     try {
    //         UserManager.saveUser(user);
    //         System.out.println("User saved successfully.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     // Test getting a user
    //     try {
    //         User retrievedUser = UserManager.getUser("TestUser");
    //         if (retrievedUser != null) {
    //             System.out.println("User found: " + retrievedUser.getUsername());
    //         } else {
    //             System.out.println("User not found.");
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    }
}
