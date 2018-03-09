package edu.knoldus.studentclassroomapplication;

import edu.knoldus.studentclassroomapplication.models.ClassRoom;
import edu.knoldus.studentclassroomapplication.models.Student;
import edu.knoldus.studentclassroomapplication.services.Operations;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OperationsTest {

    private static Operations operations;
    private static ClassRoom firstRoom;
    private static ClassRoom secondRoom;
    private static ClassRoom thirdRoom;
    static List<ClassRoom> roomsList = new ArrayList<>();
    static int ONE = 1;
    static int TWO = 2;
    static int THREE = 3;
    static int FOUR = 4;

    @BeforeClass
    public static void setUp() {
        operations = new Operations();

        List<String> subjectList = new ArrayList<>();
        subjectList.add("Java");
        subjectList.add("Scala");
        subjectList.add("Kafka");

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(ONE, "Aman", Optional.of(subjectList)));
        studentList.add(new Student(TWO, "Sakshi", Optional.empty()));
        studentList.add(new Student(THREE, "Manas", Optional.of(subjectList)));
        studentList.add(new Student(FOUR, "Megha", Optional.of(subjectList)));


        firstRoom = new ClassRoom(ONE, Optional.of(studentList.subList(TWO, THREE)));
        secondRoom = new ClassRoom(TWO, Optional.empty());
        thirdRoom = new ClassRoom(THREE, Optional.of(studentList));

        roomsList.add(firstRoom);
        roomsList.add(secondRoom);
        roomsList.add(thirdRoom);
    }

    @Test
    public void shouldGreetStudentsIfRoomHasStudents() {
        String expectedResult = "Hello students..!";
        String actualResult = operations.greetStudents(thirdRoom);
        assertEquals("Did not greet students if students present in room", actualResult, expectedResult);

    }

    @Test
    public void shouldReturnEmptyStringIfNoStudentsInRoom() {
        String expectedResult = " ";
        String actualResult = operations.greetStudents(secondRoom);
        assertEquals("Did not return empty string if students were not present", actualResult, expectedResult);
    }

    @Test
    public void shouldReturnStudentNameWithNoSubjects() {
        List<Student> expectedResult = new ArrayList<>();
        expectedResult.add(new Student(2, "Sakshi", Optional.empty()));
        List<Student> actualResult = operations.getStudentListWithNoSubjects(thirdRoom);
        assertEquals("Didn't return the student which had no subjects",
                actualResult.toString(),
                expectedResult.toString());
    }

    @Test
    public void shouldReturnEmptyListIfStudentHasSubjects() {
        List<Student> expectedResult = new ArrayList<>();
        List<Student> actualResult = operations.getStudentListWithNoSubjects(firstRoom);
        assertEquals("Didn't return empty list", actualResult, expectedResult);
    }

    @Test
    public void shouldReturnSubjectsListOfParticularRoomId() {
        List<String> subjectList = new ArrayList<>();
        subjectList.add("Java");
        subjectList.add("Scala");
        subjectList.add("Kafka");
        Stream<List<String>> expectedResult = Arrays.asList(subjectList).stream();
        Stream<List<String>> actualResult = operations.getSubjectsOfStudentsWithParticularRoomId(roomsList, ONE);
        assertEquals("Didn't return required list for room one", actualResult.collect(Collectors.toList()).toString(), expectedResult.collect(Collectors.toList()).toString());
    }


    @Ignore
    @Test
    public void shouldNotReturnSubjectsListOfParticularRoomId() {
        List<String> subjectList = new ArrayList<>();
        Stream<List<String>> expectedResult = Collections.singletonList(subjectList).stream();
        Stream<List<String>> actualResult = operations.getSubjectsOfStudentsWithParticularRoomId(roomsList, TWO);
        assertEquals("Didn't return empty list for room two", actualResult.collect(Collectors.toList()).toString(), expectedResult.collect(Collectors.toList()).toString());
    }

    @AfterClass
    public static void tearDown() {

    }
}

