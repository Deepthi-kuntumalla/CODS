import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
private static final Scanner scanner = new Scanner(System.in);
private static final StudentManagementSystem sms = new StudentManagementSystem();
public static void main(String[] args) {
while (true) {
System.out.println("\n=== Student Management System ===");
System.out.println("1. Add Student");
System.out.println("2. Edit Student");
System.out.println("3. Search Student");
System.out.println("4. Remove Student");
System.out.println("5. Display All Students");
System.out.println("6. Exit");
System.out.print("Choose an option: ");
String choice = scanner.nextLine();
switch (choice) {
case "1": addStudent(); break;
case "2": editStudent(); break;
case "3": searchStudent(); break;
case "4": removeStudent(); break;
case "5": displayStudents(); break;
case "6": System.exit(0);
default: System.out.println("Invalid choice.");
}
}
}
private static void addStudent() {
System.out.println("Enter student details:");
String name = promptNonEmpty("Name");
String roll = promptNonEmpty("Roll Number");
if (sms.searchStudent(roll) != null) {
System.out.println("Student with this roll number already exists!");
return;
}
String grade = promptNonEmpty("Grade");
String email = promptEmail();
sms.addStudent(new Student(name, roll, grade, email));
System.out.println("Student added successfully.");
}
private static void editStudent() {
String roll = promptNonEmpty("Enter Roll Number of student to edit");
Student s = sms.searchStudent(roll);
if (s == null) {
System.out.println("Student not found.");
return;
}
System.out.println("Editing student: " + s);
String name = promptNonEmpty("New Name");
String grade = promptNonEmpty("New Grade");
String email = promptEmail();
sms.editStudent(roll, name, grade, email);
System.out.println("Student updated successfully.");
}
private static void searchStudent() {
String roll = promptNonEmpty("Enter Roll Number to search");
Student s = sms.searchStudent(roll);
if (s != null) {
System.out.println(s);
} else {
System.out.println("Student not found.");
}
}
private static void removeStudent() {
String roll = promptNonEmpty("Enter Roll Number to remove");
sms.removeStudent(roll);
System.out.println("Student removed (if existed).");
}
private static void displayStudents() {
System.out.println("\nAll Students:");
for (Student s : sms.getAllStudents()) {
System.out.println(s);
}
}
private static String promptNonEmpty(String field) {
while (true) {
System.out.print(field + ": ");
String input = scanner.nextLine().trim();
if (!input.isEmpty()) return input;
System.out.println(field + " cannot be empty.");
}
}
private static String promptEmail() {
while (true) {
System.out.print("Email: ");
String email = scanner.nextLine().trim();
if (Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email)) return email;
System.out.println("Invalid email format.");
}
}
}
