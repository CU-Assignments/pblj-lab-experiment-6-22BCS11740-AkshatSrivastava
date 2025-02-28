// implement Java program that uses lambda expressions and Stream API to filter students who scored above 75%, sort them by marks, and display their names.

// Step 1: Create the Student Class
// - Define a Student class with attributes:
//     name (String)
//     marks (double)
// - Implement a constructor to initialize these values.
// - Add a display method to print student details.

// Step 2: Create the StudentFilterSort Class
// - Create a list of students with sample data.
// - Use Streams Class to:
//       Filter students who scored above 75%.
//       Sort students by marks in descending order.
//       Collect the results into a new list.
// - Use forEach() with a method reference to display results.ts.


// Test Case	                        Input Data	                                                 Expected Output
// Case 1:         Normal Case	Alice (80), Bob (72), Charlie (90), David (65), Eve (85)	         Charlie, Eve, Alice (Sorted by marks)
// Case 2:         All Below 75%	Bob (70), David (60), Frank (65)	                               No output (Empty List)
// Case 3:         Same Marks	Alice (80), Bob (80), Charlie (85)                                 Charlie, Alice, Bob (Sorted by marks, then by name)
// Case 4:         Single Student Above 75%	Alice (60), Bob (50), Charlie (90)	                 Charlie (Only one student)


import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    String getName() {
        return name;
    }

    double getMarks() {
        return marks;
    }

    void display() {
        System.out.println(name + " - Marks: " + marks);
    }
}

public class StudentFilterSort62 {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    static void processAndDisplay(List<Student> students) {
        List<Student> filteredSortedStudents = students.stream()
            .filter(s -> s.getMarks() > 75) 
            .sorted(Comparator.comparingDouble(Student::getMarks).reversed()
                .thenComparing(Student::getName)) 
            .collect(Collectors.toList());

        if (filteredSortedStudents.isEmpty()) {
            System.out.println("No students scored above 75%");
        } else {
            filteredSortedStudents.forEach(Student::display);
        }
        System.out.println("-------------------");
    }

    static void testCase1() {
        System.out.println("Test Case 1: Normal Case");
        List<Student> students = Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 72),
            new Student("Charlie", 90),
            new Student("David", 65),
            new Student("Eve", 85)
        );
        processAndDisplay(students);
    }

    static void testCase2() {
        System.out.println("Test Case 2: All Below 75%");
        List<Student> students = Arrays.asList(
            new Student("Bob", 70),
            new Student("David", 60),
            new Student("Frank", 65)
        );
        processAndDisplay(students);
    }

    static void testCase3() {
        System.out.println("Test Case 3: Same Marks");
        List<Student> students = Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 80),
            new Student("Charlie", 85)
        );
        processAndDisplay(students);
    }

    static void testCase4() {
        System.out.println("Test Case 4: Single Student Above 75%");
        List<Student> students = Arrays.asList(
            new Student("Alice", 60),
            new Student("Bob", 50),
            new Student("Charlie", 90)
        );
        processAndDisplay(students);
    }
}
