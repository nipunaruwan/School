package Controller;

import Controller.CrudInterface.StudentSaveController ;
import Model.Student;
import Util.UtillController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class AttendenceController implements Initializable{
    public AnchorPane AttendContext;
    public TextField txtStudentID;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtStudentName;
    public TextField txtEmail;
    public TextField txtCity;
    public ComboBox cmbCourse;
    public Label lblDate;
    public TableColumn StudentIDColum;
    public TableColumn StudentNamecolum;
    public TableColumn AddressColum;
    public TableColumn EmailColum;
    public TableColumn ContactNoColum;
    public TableColumn CityColum;
    public TableView<StudentTm> StudentTable;
    public Button btnSave;
    ObservableList<StudentTm> obList = FXCollections.observableArrayList();
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(S0-)[0-9]{1,3}$");
    Pattern TownPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern AddressPattern = Pattern.compile("^[A-z]{1,9}$");
    Pattern NamePattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern CityPattern = Pattern.compile("^[A-z]{1,9}$");
    Pattern eMailPattern = Pattern.compile("^[a-z]{2,}(@)[a-z]{2,}(.com)$");
    Pattern NICPattern = Pattern.compile("^[0-9]{9,13}[v]?$");
    Pattern contactPattern = Pattern.compile("^([+94]?[0-9]{10}|((0)[0-9]{9}))$");
    Pattern noPattern = Pattern.compile("^[0-9]{4,}$");

  /*  public void initialize() {
        try {
            loadCourseID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StudentIDColum.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        StudentNamecolum.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        AddressColum.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailColum.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ContactNoColum.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        CityColum.setCellValueFactory(new PropertyValueFactory<>("City"));




       try {
            setComment(new StudentSaveController().getAllStudents());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        btnSave.setDisable(false);
        storeValidations();

    }
*/
    private void storeValidations() {
        map.put(txtStudentID, idPattern);
        map.put(txtStudentName, NamePattern);
        map.put(txtAddress, AddressPattern);
        map.put(txtEmail, eMailPattern);
        map.put(txtContact, contactPattern);
        map.put(txtCity, CityPattern);


    }

    public void btnAttendCancel(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) AttendContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
    }

    public void btnSeach(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Student s1=new Student(txtStudentID.getText(),txtStudentName.getText(),txtAddress.getText(),
                txtEmail.getText(),txtContact.getText(),txtCity.getText(),(String)cmbCourse.getValue(),
                lblDate.getText());

        if(new StudentSaveController().saveStudent(s1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved....").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again....").show();
        }
    }

    public void loadCourseID() throws SQLException, ClassNotFoundException {
        List<String> courseIds = null;
        try {
            courseIds = new CourseSaveController().getCourseID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (courseIds != null) {
            cmbCourse.getItems().addAll(courseIds);
        }

    }

    public void setComment(ArrayList<Student> students) {
        students.forEach(e -> {
            obList.add(
                    new StudentTm(e.getStudentID(), e.getStudentName(), e.getAddress(), e.getEmail(), e.getContactNo(), e.getCity()));
        });
        StudentTable.setItems(obList);

    }

    public void studentkey(KeyEvent keyEvent) {
        Object response = UtillController.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCourseID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StudentIDColum.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        StudentNamecolum.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        AddressColum.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailColum.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ContactNoColum.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        CityColum.setCellValueFactory(new PropertyValueFactory<>("City"));




        try {
            setComment(new StudentSaveController().getAllStudents());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        btnSave.setDisable(false);
        storeValidations();


    }



   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentIDColum.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        StudentNamecolum.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        AddressColum.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailColum.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ContactNoColum.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        CityColum.setCellValueFactory(new PropertyValueFactory<>("City"));




        try {
         setComment(new StudentSaveController().getAllStudents());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
    }*/
}