import java.io.*;
import java.util.*;
public class StudentManagementSystem {
private List<Student> students;
private static final String FILE_NAME = "students.dat";
public StudentManagementSystem() {
students = loadFromFile();
}
public void addStudent(Student student) {
students.add(student);
saveToFile();
}
public void removeStudent(String rollNumber) {
students.removeIf(s -> s.getRollNumber().equalsIgnoreCase(rollNumber));
saveToFile();
}
public Student searchStudent(String rollNumber) {
for (Student s : students) {
if (s.getRollNumber().equalsIgnoreCase(rollNumber)) return s;
}
return null;
}
public List<Student> getAllStudents() {
return students;
}
public void editStudent(String rollNumber, String name, String grade, String email) {
Student s = searchStudent(rollNumber);
if (s != null) {
s.setName(name);
s.setGrade(grade);
s.setEmail(email);
saveToFile();
}
}
private void saveToFile() {
try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
out.writeObject(students);
} catch (IOException e) {
System.out.println("Error saving data: " + e.getMessage());
}
}
private List<Student> loadFromFile() {
try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
return (List<Student>) in.readObject();
} catch (Exception e) {
return new ArrayList<>();
}
}
}
