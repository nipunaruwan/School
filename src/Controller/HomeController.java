package Controller;

import db.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HomeController {

    public AnchorPane HomeA;

    public Label lblD;
    public Label lbltime;
    public AnchorPane D;
    public PieChart courseChart;

    public void initialize() {
        initClock();

        try {
            setChart();
        } catch (Exception e) {

        }
    }

    private void setChart() throws SQLException, ClassNotFoundException {
        ObservableList<PieChart.Data> courses = FXCollections.observableArrayList();
        ArrayList<String> allCourses = getAllCourses();
        for (String id:allCourses) {
            String name=getCourseName(id);
            int count = getStudents(id);
            courses.add(new PieChart.Data(name,count));

        }
        courseChart.setData(courses);
    }

    private int getStudents(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select count(StudentID) from  CourseDetails where courseID='"+id+"'").executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    private String getCourseName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT CourseName FROM Course WHERE courseID='"+id+"'").executeQuery();
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    private ArrayList<String> getAllCourses() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT CourseID FROM Course").executeQuery();
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;

    }

    public void btnuser(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentForm.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
        HomeA.getChildren().clear();
        HomeA.getChildren().add(load);
    }

    public void btnexam(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/Exam.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
        HomeA.getChildren().clear();
        HomeA.getChildren().add(load);
    }

    public void btntimetable(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/timetable.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
       HomeA.getChildren().clear();
       HomeA.getChildren().add(load);
    }

    public void btnreport(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentReport.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
        HomeA.getChildren().clear();
       HomeA.getChildren().add(load);
    }

    public void btnlibrary(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LibraryForm.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
        HomeA.getChildren().clear();
       HomeA.getChildren().add(load);
    }

    public void btnatt(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/Attendence.fxml");
        assert resource != null;
        Parent load= FXMLLoader.load(resource);
        HomeA.getChildren().clear();
        HomeA.getChildren().add(load);
    }

    public void btnlogout(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) HomeA.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserLoginForm.fxml"))));
    }
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lbltime.setText(LocalDateTime.now().format(formatter));


            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            lblD.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnClass(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/Course.fxml");
        Parent load = FXMLLoader.load(resource);
       HomeA.getChildren().clear();
        HomeA.getChildren().add(load);
    }

    public void studentForm(ActionEvent actionEvent) {
        try {
            JasperDesign Design = JRXmlLoader.load(this.getClass().getResourceAsStream("../View/report/StudentForm" +
                    ".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(Design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
