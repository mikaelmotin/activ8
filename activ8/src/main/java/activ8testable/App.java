package activ8testable;

import java.io.IOException;

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
        
        
        //activ8testable.UserManager.getUser(user.getUuid()).addStudySet(folder, set3);;
        // UUID apa = UUID.fromString("e1fd6696-59bb-4c0e-8f6b-4cd902db3238");
        // activ8testable.User user = userManager.getUser(apa);
        // System.out.println(user.getUsername());
        //activ8testable.UserManager.saveUserData(user);

        for(StudyFolder f: user.getStudyFolders()) {
            for(StudySet s: f.getStudySets()) {
                System.out.println(s.getTitle());
            }
        }

































    //     activ8testable.Flashcard card1 = new activ8testable.Flashcard("apa", "monkey");
    //     activ8testable.Flashcard card2 = new activ8testable.Flashcard("hund", "dawg");
    //     activ8testable.Flashcard card3 = new activ8testable.Flashcard("katt", "cat");
    //     activ8testable.Flashcard card4 = new activ8testable.Flashcard("groda", "frog");

    //     activ8testable.StudySet studySet = new activ8testable.StudySet("Swedish-English glossary");
    //     studySet.addFlashcard(card1);
    //     studySet.addFlashcard(card2);
    //     studySet.addFlashcard(card3);
    //     studySet.addFlashcard(card4);

    //     studySet.editFlashcard(card4, "321123", "423423");

    //     activ8testable.StudySet studySet2 = new activ8testable.StudySet("St2");
    //     studySet2.addFlashcard(card1);
    //     studySet2.addFlashcard(card2);
    //     studySet2.addFlashcard(card3);
    //     studySet2.addFlashcard(card4);

    //     activ8testable.User user = new activ8testable.User("TestUser");
    //     user.addStudySet(studySet);
    //     user.addStudySet(studySet2);



    //     // Test saving a user
    //     try {
    //         activ8testable.UserManager.saveUser(user);
    //         System.out.println("activ8testable.User saved successfully.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     // Test getting a user
    //     try {
    //         activ8testable.User retrievedUser = activ8testable.UserManager.getUser("TestUser");
    //         if (retrievedUser != null) {
    //             System.out.println("activ8testable.User found: " + retrievedUser.getUsername());
    //         } else {
    //             System.out.println("activ8testable.User not found.");
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    }
}
