package Controller.CrudInterface;

import Model.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamDetails {
    boolean saveExam(Exam exam) throws SQLException, ClassNotFoundException;
    boolean updateExam(Exam exam) throws SQLException, ClassNotFoundException;
    boolean deleteExam(Exam exam) throws SQLException, ClassNotFoundException;
    Exam getExam(Exam exam);
    ArrayList<Exam> getAllExam() throws SQLException, ClassNotFoundException;
}
