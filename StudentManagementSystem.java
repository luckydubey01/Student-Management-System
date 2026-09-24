import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student details store karne ke liye Model class (Encapsulation)
class Student {
    private final int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Age: %-3d | Course: %s", id, name, age, course);
    }
}

// Main System Class
public class StudentManagementSystem {
    private static final List<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==============================================");
        System.out.println("   WELCOME TO STUDENT MANAGEMENT SYSTEM       ");
        System.out.println("==============================================");

        while (running) {
            System.out.println("\n---------------- MAIN MENU ------------------");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    displayAllStudents();
                    break;
                case "3":
                    searchStudent();
                    break;
                case "4":
                    updateStudent();
                    break;
                case "5":
                    deleteStudent();
                    break;
                case "6":
                    System.out.println("\n[+] Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("[-] Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    // 1. Naya Student add karne ke liye
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        int id = getValidInt("Enter Student ID: ");

        // Check if ID already exists
        if (findStudentById(id) != null) {
            System.out.println("[-] Error: Student with ID " + id + " already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        int age = getValidInt("Enter Age: ");

        System.out.print("Enter Course: ");
        String course = scanner.nextLine().trim();

        studentList.add(new Student(id, name, age, course));
        System.out.println("[+] Student added successfully!");
    }

    // 2. Sabhi Students ko print karne ke liye
    private static void displayAllStudents() {
        System.out.println("\n--- Student List ---");
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // 3. ID ke dwara Student search karne ke liye
    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int id = getValidInt("Enter Student ID to search: ");

        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("[+] Student Found:\n" + student);
        } else {
            System.out.println("[-] Student with ID " + id + " not found.");
        }
    }

    // 4. Existing Student ki details update karne ke liye
    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int id = getValidInt("Enter Student ID to update: ");

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("[-] Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Current Details: " + student);
        System.out.print("Enter New Name (leave blank to keep unchanged): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            student.setName(newName);
        }

        System.out.print("Enter New Age (enter 0 to keep unchanged): ");
        String ageInput = scanner.nextLine().trim();
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                if (newAge > 0) student.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("[-] Invalid age input. Keeping old age.");
            }
        }

        System.out.print("Enter New Course (leave blank to keep unchanged): ");
        String newCourse = scanner.nextLine().trim();
        if (!newCourse.isEmpty()) {
            student.setCourse(newCourse);
        }

        System.out.println("[+] Student details updated successfully!");
    }

    // 5. Student delete karne ke liye
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = getValidInt("Enter Student ID to delete: ");

        Student student = findStudentById(id);
        if (student != null) {
            studentList.remove(student);
            System.out.println("[+] Student record deleted successfully!");
        } else {
            System.out.println("[-] Student with ID " + id + " not found.");
        }
    }

    // Helper Method: ID se student dhundne ke liye
    private static Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Helper Method: Input integer validation
    private static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[-] Invalid input. Please enter a valid number.");
            }
        }
    }
}