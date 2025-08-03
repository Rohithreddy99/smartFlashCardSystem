package service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SubjectClassifier {
    private static final Map<String, String> KEYWORD_TO_SUBJECT = new HashMap<>();
    static {
        // Example rules—extend as needed!
        KEYWORD_TO_SUBJECT.put("photosynthesis", "Biology");
        KEYWORD_TO_SUBJECT.put("chlorophyll", "Biology");
        KEYWORD_TO_SUBJECT.put("cell", "Biology");
        KEYWORD_TO_SUBJECT.put("mitosis", "Biology");
        KEYWORD_TO_SUBJECT.put("evolution", "Biology");
        KEYWORD_TO_SUBJECT.put("velocity", "Physics");
        KEYWORD_TO_SUBJECT.put("acceleration", "Physics");
        KEYWORD_TO_SUBJECT.put("force", "Physics");
        KEYWORD_TO_SUBJECT.put("newton", "Physics");
        KEYWORD_TO_SUBJECT.put("gravity", "Physics");
        KEYWORD_TO_SUBJECT.put("derivative", "Mathematics");
        KEYWORD_TO_SUBJECT.put("integral", "Mathematics");
        KEYWORD_TO_SUBJECT.put("polynomial", "Mathematics");
        KEYWORD_TO_SUBJECT.put("equation", "Mathematics");
        KEYWORD_TO_SUBJECT.put("algebra", "Mathematics");
        KEYWORD_TO_SUBJECT.put("history", "History");
        KEYWORD_TO_SUBJECT.put("war", "History");
        KEYWORD_TO_SUBJECT.put("revolution", "History");
        KEYWORD_TO_SUBJECT.put("constitution", "Civics");
        KEYWORD_TO_SUBJECT.put("government", "Civics");
        KEYWORD_TO_SUBJECT.put("economics", "Economics");
        KEYWORD_TO_SUBJECT.put("supply", "Economics");
        KEYWORD_TO_SUBJECT.put("demand", "Economics");
        KEYWORD_TO_SUBJECT.put("culture", "Social Studies");
        KEYWORD_TO_SUBJECT.put("society", "Social Studies"); 
        // can add more keywords and subjects as needed       
    }

    public String inferSubject(String question, String answer) {
        String text = (question + " " + answer).toLowerCase();
        for (Map.Entry<String, String> entry : KEYWORD_TO_SUBJECT.entrySet()) {
            if (text.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "General"; // default /fallback
    }
}
