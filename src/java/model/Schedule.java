package model;

import java.sql.Timestamp;

public class Schedule {
    private String courseName;
    private Timestamp time;
    private int slot;
    private String dayName;

    // Default constructor
    public Schedule() {
    }

    // Parameterized constructor
    public Schedule(String courseName, Timestamp time, int slot, String dayName) {
        this.courseName = courseName;
        this.time = time;
        this.slot = slot;
        this.dayName = dayName;
    }

    // Getters and setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
