package Controller.CrudInterface;

import Model.Exam;
import Model.Student;
import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentSaveController {

    private Object SQLException;

    public boolean saveStudent(Student s1){
        Connection con=null;

        try {
            //con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            DbConnection.getInstance().getConnection();
            PreparedStatement stm=con.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?,?)");

            stm.setString(1,s1.getStudentID());
            stm.setString(2,s1.getStudentName());
            stm.setString(3,s1.getAddress());
            stm.setString(4,s1.getEmail());
            stm.setString(5,s1.getContactNo());
            stm.setString(6,s1.getCity());
         //   stm.setString(7, s1.getDate());

            if(stm.executeUpdate()>0){
                if(addStudentForCourse(s1.getStudentID(),s1.getCourseID(),s1.getDate())){
                    con.commit();
                }else{
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private boolean addStudentForCourse(String studentID, String courseID, String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO CourseDetails " +
                "VALUES (?,?,?)");
        stm.setString(1,courseID);
        stm.setString(2,studentID);
        stm.setString(3,date);

        return stm.executeUpdate()>0;
    }


    public ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student");
            ResultSet rst = stm.executeQuery();
            ArrayList<Student> Students = new ArrayList<>();
            while (rst.next()) {
                Students.add(new Student(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6)

                ));
            }
            return Students;
        }
    }





