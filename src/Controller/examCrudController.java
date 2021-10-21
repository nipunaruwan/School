package Controller;

import Controller.CrudInterface.ExamDetails;
import Model.Exam;
import db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class examCrudController implements ExamDetails {

    @Override
    public boolean saveExam(Exam exam) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO exam VALUES (?,?,?,?)");
        preparedStatement.setObject(1, exam.getExamId());
        preparedStatement.setObject(2, exam.getUserId());
        preparedStatement.setObject(3, exam.getDate());
        preparedStatement.setObject(4, exam.getTime());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateExam(Exam exam) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE user SET name=?,Contact=?,Email=?,UserName=?,Password=?,UserType=? WHERE UserID=?");

        stm.setObject(1, exam.getExamId());
        stm.setObject(2, exam.getUserId());
        stm.setObject(3, exam.getDate());
        stm.setObject(4, exam.getTime());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteExam(Exam exam) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Exam getExam(Exam exam) {
        return null;
    }







   /*@Override
    public boolean deleteExam(String examID) throws SQLException, ClassNotFoundException {
       if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Exam WHERE ExamID='"+examID+"'"){
            return true;
       }else {
           return EexecuteUpdate()>0);
        }*/


    /*@Override
    public Exam getExam(String examID) {
        return null;
    }*/

    @Override
    public ArrayList<Exam> getAllExam() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM exam");
        ResultSet rst = stm.executeQuery();
        ArrayList<Exam> exams = new ArrayList<>();
        while (rst.next()) {
            exams.add(new Exam(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return exams;
    }
}
