# Smart Flashcard Backend

## Features
- Add new flashcards with subject automatically inferred (Physics, Biology, etc).
- Get flashcards by student, with a mix of different subjects.

## Setup & Run

**1. Clone repository**

    git clone <your-repo-url>
    cd <repo-name>

**2. Build & run**

    ./mvnw spring-boot:run

(or use your IDE, run `main()` in `FlashcardApplication`)

**3. API Usage**

- **Add flashcard:**  
  POST `/flashcard` with JSON:
    {
      "student_id": "stu001",
      "question": "What is Newton's Second Law?",
      "answer": "Force equals mass times acceleration"
    }

- **Get mixed subject flashcards:**  
  GET `/get-subject?student_id=stu001&limit=5`

## Notes

- Uses in-memory storage for simplicity (not persistent after restart).
- To extend subjects, edit `SubjectClassifier.java`.
