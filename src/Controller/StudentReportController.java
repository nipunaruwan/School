package Controller;

import Model.Student;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class StudentReportController implements Initializable {
    public ComboBox<String> reportID;
    Student student;
    public void btngetreport(ActionEvent actionEvent) throws JRException {

        HashMap map = new HashMap();
        map.put("name",student.getStudentName());
        map.put("address",student.getAddress());
        map.put("email",student.getEmail());
        map.put("contactNo",student.getContactNo());
        map.put("city",student.getCity());
        map.put("gender",student.getCourseID());

        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/Report/Student_A4.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource(1));
        JasperViewer.viewReport(jasperPrint,false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ArrayList<String> Idlist = getallstudentID();
            reportID.getItems().addAll(Idlist);
            reportID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    loadStudent(newValue);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void loadStudent(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student WHERE studentID='"+id+"'").executeQuery();
        ArrayList<String> ids = new ArrayList<>();
        if (rst.next()) {
           student=new Student(rst.getString(1),
                   rst.getString(2),
                   rst.getString(3),
                   rst.getString(4),
                   rst.getString(5),
                   rst.getString(6),
                   rst.getString(7),
                   rst.getString(8));
        }
    }

    private ArrayList<String> getallstudentID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT StudentID FROM Student").executeQuery();
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;

    }
}
