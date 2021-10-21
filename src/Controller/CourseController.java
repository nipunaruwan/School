package Controller;

import Model.Course;
import Util.UtillController;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.CourseTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CourseController {
    public AnchorPane UserContext;
    public TextField CourseID;
    public TextField CName;
    public ComboBox CType;
    public TableView<CourseTm> CoursetableID;
    public TableColumn CourseIDC;
    public TableColumn CourseNameC;
    public TableColumn CourseTypeC;
    public Label CID;
    public Button btnSave;
    ObservableList<CourseTm> obList = FXCollections.observableArrayList();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C0-)[0-9]{1,3}$");
    Pattern namepattern = Pattern.compile("^[A-z]{3,40}$");

    private void storeValidations() {
        map.put(CourseID, idPattern);
        map.put(CName, namepattern);


    }

    public void initialize() {
        CType.getItems().addAll("Full Time", "Part Time");

        CourseIDC.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        CourseNameC.setCellValueFactory(new PropertyValueFactory<>("Coursename"));
        CourseTypeC.setCellValueFactory(new PropertyValueFactory<>("CourseType"));

        try {
            setCourse(new CourseSaveController().getAllCourse());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        loadCID();

        btnSave.setDisable(false);
        storeValidations();
    }

    public void btnSaveonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Course course = new Course(CourseID.getText(), CName.getText(), (String) CType.getValue());
        if (new CourseSaveController().savecourse(course)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Try again").show();
        }
    }

    public void btnDeleteOnaction(ActionEvent actionEvent) {
    }

    public void btnUpdateonaction(ActionEvent actionEvent) {
    }

    public void setCourse(ArrayList<Course> courses) {
        courses.forEach(e -> {
            obList.add(
                    new CourseTm(e.getCourseID(),e.getCoursename(),e.getCourseType()));
        });
        CoursetableID.setItems(obList);

    }
    private void loadCID() {
        try {
            int c = 0;
            ResultSet rs = DbConnection.search("select count(CourseID) AS x from Course");
            if (rs.next()) {
                c = Integer.parseInt(rs.getString("x"));
                c++;
                CID.setText("CourseID" + c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CourseAdd(KeyEvent keyEvent) {
        Object response = UtillController.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }

    }

    public void btncancel(ActionEvent actionEvent) {
    }
}

