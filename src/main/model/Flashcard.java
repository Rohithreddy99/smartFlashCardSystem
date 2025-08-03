package model;

public class Flashcard {
    private String studentId;
    private String question;
    private String answer;
    private String subject;

    public Flashcard(String studentId, String question, String answer, String subject) {
        this.studentId = studentId;
        this.question = question;
        this.answer = answer;
        this.subject = subject;
    }

    // Getters and setters omitted for brevity
    // ... (generate using IDE or manually)
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
