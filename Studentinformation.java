import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a student
class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<AcademicRecord> academicRecords;

    public Student(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.academicRecords = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void addAcademicRecord(AcademicRecord record) {
        this.academicRecords.add(record);
    }

    public List<AcademicRecord> getAcademicRecords() {
        return academicRecords;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}

// Class representing an academic record
class AcademicRecord {
    private String course;
    private String grade;

    public AcademicRecord(String course, String grade) {
        this.course = course;
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Course: " + course + ", Grade: " + grade;
    }
}

// Class representing the Student Information System
public class StudentInformationSystem {
    private List<Student> students;
    private Scanner scanner;

    public StudentInformationSystem() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nStudent Information System");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Add Academic Record");
            System.out.println("4. View Academic Records");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    addAcademicRecord();
                    break;
                case 4:
                    viewAcademicRecords();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Student Phone: ");
        String phone = scanner.nextLine();

        Student student = new Student(id, name, email, phone);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private void viewStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void addAcademicRecord() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("Enter Course: ");
            String course = scanner.nextLine();
            System.out.print("Enter Grade: ");
            String grade = scanner.nextLine();
            AcademicRecord record = new AcademicRecord(course, grade);
            student.addAcademicRecord(record);
            System.out.println("Academic record added successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void viewAcademicRecords() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            List<AcademicRecord> records = student.getAcademicRecords();
            if (records.isEmpty()) {
                System.out.println("No academic records found.");
            } else {
                for (AcademicRecord record : records) {
                    System.out.println(record);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();
        sis.start();
    }
}
