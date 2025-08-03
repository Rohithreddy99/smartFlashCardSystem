package repository;

import com.yourorg.flashcard.model.Flashcard;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FlashcardRepository {
    private final List<Flashcard> flashcards = new ArrayList<>();

    public void addFlashcard(Flashcard card) {
        flashcards.add(card);
    }

    public List<Flashcard> getFlashcardsByStudent(String studentId) {
        return flashcards.stream()
                .filter(card -> card.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }
}
