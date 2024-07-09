/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Mark {
    private int markID;
    private double assigment;
    private double pt1;
    private double pt2;
    private double fe;
    private double average;
    private Instructor instructor;
    private Student student;
    private Course course;
    private Semester semester;
    private StudentClass studentclass;

    public Mark() {
    }

    public Mark(int markID, double assigment, double pt1, double pt2, double fe, double average, Instructor instructor, Student student, Course course, Semester semester, StudentClass studentclass) {
        this.markID = markID;
        this.assigment = assigment;
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.fe = fe;
        this.average = average;
        this.instructor = instructor;
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.studentclass = studentclass;
    }

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public double getAssigment() {
        return assigment;
    }

    public void setAssigment(double assigment) {
        this.assigment = assigment;
    }

    public double getPt1() {
        return pt1;
    }

    public void setPt1(double pt1) {
        this.pt1 = pt1;
    }

    public double getPt2() {
        return pt2;
    }

    public void setPt2(double pt2) {
        this.pt2 = pt2;
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public StudentClass getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(StudentClass studentclass) {
        this.studentclass = studentclass;
    }

  
    
}
