package model;

import java.sql.Timestamp;

public class ExamSchedule {

    private String studentName;
    private String courseName;
    private String subjectName;
    private String assessmentName;
    private Timestamp examDate;
    private float examDuration;
    private String examRoom;
    private Timestamp scoreDate;

    // Getters and setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Timestamp getExamDate() {
        return examDate;
    }

    public void setExamDate(Timestamp examDate) {
        this.examDate = examDate;
    }

    public float getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(float examDuration) {
        this.examDuration = examDuration;
    }

    public String getExamRoom() {
        return examRoom;
    }

    public void setExamRoom(String examRoom) {
        this.examRoom = examRoom;
    }

    public Timestamp getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Timestamp scoreDate) {
        this.scoreDate = scoreDate;
    }
}
