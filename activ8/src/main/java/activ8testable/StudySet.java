package activ8testable;

import java.util.List;

public interface StudySet {

    void addCard(Card card);
    void removeCard(Card card);
    void editTitle(String title);
    void setDescription(String description);
    String getDescription();

    List<Card> getCardsList(String difficulty);

    String getTitle();
    int getSize();
}
