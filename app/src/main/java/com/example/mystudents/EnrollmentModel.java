package com.example.mystudents;

public class EnrollmentModel {
    String id;
    String studentname;

    public EnrollmentModel(String id, String studentname) {
        this.id = id;
        this.studentname = studentname;
    }

    public String getId() {
        return id;
    }

    public String getStudentname() {
        return studentname;
    }
}
