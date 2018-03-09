package edu.knoldus.studentclassroomapplication;

import edu.knoldus.studentclassroomapplication.models.ClassRoom;
import edu.knoldus.studentclassroomapplication.models.Student;
import edu.knoldus.studentclassroomapplication.services.Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainApp {

    public static void main(String[] args) {

        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;

        List<String> subjectList = new ArrayList<>();
        subjectList.add("Java");
        subjectList.add("Scala");
        subjectList.add("Kafka");

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(one, "Aman", Optional.of(subjectList)));
        studentList.add(new Student(two, "Sakshi", Optional.empty()));
        studentList.add(new Student(three, "Manas", Optional.of(subjectList)));
        studentList.add(new Student(four, "Megha", Optional.of(subjectList)));

        List<ClassRoom> roomsList = new ArrayList<>();

        ClassRoom firstRoom = new ClassRoom(one, Optional.of(studentList.subList(two,three)));
        ClassRoom secondRoom = new ClassRoom(two, Optional.empty());
        ClassRoom thirdRoom = new ClassRoom(three, Optional.of(studentList));

        roomsList.add(firstRoom);
        roomsList.add(secondRoom);
        roomsList.add(thirdRoom);

        Operations operations = new Operations();

        System.out.println("Students with no subjects associated =");
        operations.getStudentListWithNoSubjects(thirdRoom)
                .forEach(studentsList -> System.out.println(studentsList.getName()));

        System.out.println("\nSubjects studied by students in room number 1 =");
        operations.getSubjectsOfStudentsWithParticularRoomId(roomsList, one)
                .forEach(System.out::println);

        System.out.println("\nIf students are present greet them ");
        System.out.println(operations.greetStudents(thirdRoom));
    }

}
