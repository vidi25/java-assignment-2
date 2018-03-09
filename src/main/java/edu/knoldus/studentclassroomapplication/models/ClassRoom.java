package edu.knoldus.studentclassroomapplication.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassRoom {

    private int roomId;
    private Optional<List<Student>> studentList;

    public ClassRoom(int roomId, Optional<List<Student>> studentList) {
        this.roomId = roomId;
        this.studentList = studentList;
    }

    public int getRoomId() {
        return roomId;
    }

    public List<Student> getStudentList() {
        return studentList.orElseGet(ArrayList::new);
    }

    public boolean hasStudents() {
        return this.studentList.isPresent();
    }


}


