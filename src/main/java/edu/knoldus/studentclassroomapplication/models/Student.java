package edu.knoldus.studentclassroomapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student {

    private int rollNumber;
    private String name;
    private Optional<List<String>> subjects;

    public Student(int rollNumber, String name, Optional<List<String>> subjects) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.subjects = subjects;
    }


    public String getName() {
        return name;
    }

    public List<String> getSubjects() {
        return this.subjects.orElseGet(ArrayList::new);
    }

    public boolean hasSubjects() {
        return this.subjects.isPresent();
    }

    @Override
    public final String toString() {
        return "Roll No: " + this.rollNumber + "Name: " + this.name + "Subjects: " + this.subjects;
    }
}
