/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Instructor {
    private String instructorID;
    private String instructorName;
    private String instructorEmail;
    private int instructorPhone;

    public Instructor() {
    }

    public Instructor(String instructorID, String instructorName, String instructorEmail, int instructorPhone) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public int getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(int instructorPhone) {
        this.instructorPhone = instructorPhone;
    }
    
}
