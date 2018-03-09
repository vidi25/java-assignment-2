package edu.knoldus.studentclassroomapplication.services;

import edu.knoldus.studentclassroomapplication.models.ClassRoom;
import edu.knoldus.studentclassroomapplication.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operations {

    /**
     * this function print hello students if room has students.
     *
     * @param room object of class ClassRoom
     * @return String
     */
    public String greetStudents(ClassRoom room) {
        if (room.hasStudents()) {
            return "Hello students..!";
        } else {
            return " ";
        }

    }

    /**
     * this function returns list of students with no subjects.
     *
     * @param room object of class ClassRoom
     * @return List of students
     */
    public List<Student> getStudentListWithNoSubjects(ClassRoom room) {
        if (room.hasStudents()) {
            return room.getStudentList().stream()
                    .filter(student -> !student.hasSubjects())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * this function returns those subjects of students of a particular room.
     *
     * @param rooms  list of class ClassRooms
     * @param roomId integer
     * @return Stream of List of subjects
     */
    public Stream<List<String>> getSubjectsOfStudentsWithParticularRoomId(List<ClassRoom> rooms, int roomId) {
        return rooms.stream()
                .filter(room -> room.getRoomId() == roomId)
                .map(ClassRoom::getStudentList)
                .flatMap(students -> students
                        .stream()
                        .map(Student::getSubjects)

                );

    }
}
