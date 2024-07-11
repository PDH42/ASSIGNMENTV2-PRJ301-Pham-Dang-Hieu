package model;

public class Grade {
    private Exam exam;
    private Student student;
    private float score;
    private String courseName; // Add this attribute

    // Getters and setters
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCourseName() { // Add getter
        return courseName;
    }

    public void setCourseName(String courseName) { // Add setter
        this.courseName = courseName;
    }
}
