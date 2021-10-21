package Controller.CrudInterface;

import Model.Course;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseDetails {
    boolean saveCourse(Course course) throws SQLException, ClassNotFoundException;
    boolean updateCourse(Course course);
    boolean deleteCourse(String id);
    Course getCourse(String id);
    ArrayList<Course> getAllCourse() throws SQLException, ClassNotFoundException;
}
