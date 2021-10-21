package Controller;

import Model.Course;
import db.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSaveController {
    public List<String> getCourseID() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMSYS", "root", "1234");
        ResultSet rst=DbConnection.search("SELECT * FROM Course");
      //  PreparedStatement stm=connection.prepareStatement("SELECT * FROM Course");
        List<String> getIDs=new ArrayList<>();
      // ResultSet rst=stm.executeQuery();
        while (rst.next()){
            getIDs.add(rst.getString(1));
        }
        return getIDs;
    }

    public boolean savecourse(Course course) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Course VALUES (?,?,?)");
        stm.setObject(1, course.getCourseID());
        stm.setObject(2, course.getCoursename());
        stm.setObject(3, course.getCourseType());
        return stm.executeUpdate() > 0;
    }

    public ArrayList<Course> getAllCourse() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM course");
        ArrayList<Course> courses = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            courses.add(new Course(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3)
                    )
            );

        }

        return courses;
    }


}
