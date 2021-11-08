package com.example.mystudents;

public class StudentEnrolledModel {
    String Sname;
    String course;
    String year;
    String offno;
    String subcode;
    String sched;
    String room;
    String teachID;

    public StudentEnrolledModel(String sname, String course, String year, String offno, String subcode, String sched, String room, String teachID) {
        this.Sname = sname;
        this.course = course;
        this.year = year;
        this.offno = offno;
        this.subcode = subcode;
        this.sched = sched;
        this.room = room;
        this.teachID = teachID;
    }

    public String getSname() {
        return Sname;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getOffno() {
        return offno;
    }

    public String getSubcode() {
        return subcode;
    }

    public String getSched() {
        return sched;
    }

    public String getRoom() {
        return room;
    }

    public String getTeachID() {
        return teachID;
    }
}
