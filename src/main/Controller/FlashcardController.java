package controller;

import model.Flashcard;
import repository.FlashcardRepository;
import service.SubjectClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FlashcardController {

    @Autowired
    private FlashcardRepository flashcardRepo;

    @Autowired
    private SubjectClassifier classifier;

    @PostMapping("/flashcard")
    public Map<String, String> addFlashcard(@RequestBody Map<String, String> request) {
        String studentId = request.get("student_id");
        String question = request.get("question");
        String answer = request.get("answer");
        String subject = classifier.inferSubject(question, answer);

        Flashcard card = new Flashcard(studentId, question, answer, subject);
        flashcardRepo.addFlashcard(card);

        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Flashcard added successfully");
        resp.put("subject", subject);
        return resp;
    }

    @GetMapping("/get-subject")
    public List<Map<String, String>> getMixedSubjectFlashcards(
            @RequestParam String student_id,
            @RequestParam int limit
    ) {
        List<Flashcard> all = flashcardRepo.getFlashcardsByStudent(student_id);

        // Group by subject, shuffle each group
        Map<String, List<Flashcard>> bySubject = all.stream()
                .collect(Collectors.groupingBy(Flashcard::getSubject));

        List<Flashcard> result = new ArrayList<>();
        Random rand = new Random();

        // Mix: round-robin to ensure subject variety
        List<List<Flashcard>> subjectLists = new ArrayList<>(bySubject.values());
        int added = 0;
        while (added < limit && !subjectLists.isEmpty()) {
            Iterator<List<Flashcard>> it = subjectLists.iterator();
            while (it.hasNext() && added < limit) {
                List<Flashcard> subjectCards = it.next();
                if (!subjectCards.isEmpty()) {
                    Flashcard card = subjectCards.remove(rand.nextInt(subjectCards.size()));
                    result.add(card);
                    added++;
                }
                if (subjectCards.isEmpty()) {
                    it.remove();
                }
            }
        }
        // Shuffle the resulting list before returning
        Collections.shuffle(result, rand);

        // Convert to JSON serializable structure
        return result.stream().map(card -> {
            Map<String, String> map = new HashMap<>();
            map.put("question", card.getQuestion());
            map.put("answer", card.getAnswer());
            map.put("subject", card.getSubject());
            return map;
        }).collect(Collectors.toList());
    }
}
