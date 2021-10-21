package Controller.CrudInterface;

import Model.Student;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDetails {
    int x=2;
    boolean saveStudent(Student student) throws SQLException, ClassNotFoundException;
    boolean updateStudent(Student student);
    boolean deleteStudent(String id);
    Student getStudent(String id);
    ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException;
}
