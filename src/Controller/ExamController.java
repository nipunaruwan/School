package Controller;

import Model.Exam;
import Util.UtillController;
import com.jfoenix.controls.JFXTimePicker;
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
import view.tm.ExamTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ExamController implements Initializable {
    public TableColumn examIDc;
    public TableColumn UserIDc;
    public TableColumn Examdatec;
    public TableColumn timec;
    public TextField ExamID;
    public TextField userID;

    public DatePicker Date;
    public TableView tbrlExam;
    public JFXTimePicker examtime;
    public Button btnSave;
    public AnchorPane ExamContext;
    ObservableList<ExamTM> obList = FXCollections.observableArrayList();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(E0-)[0-9]{1,3}$");
    Pattern userIDPattern = Pattern.compile("^(User1){1,2}$");

   /* Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");*/


    private void storeValidations() {
        map.put(ExamID, idPattern);
        map.put(userID, userIDPattern);


    }




        public void initialize (URL location, ResourceBundle resources){
            examIDc.setCellValueFactory(new PropertyValueFactory<>("examId"));
            UserIDc.setCellValueFactory(new PropertyValueFactory<>("userId"));
            Examdatec.setCellValueFactory(new PropertyValueFactory<>("date"));
            timec.setCellValueFactory(new PropertyValueFactory<>("time"));
            try {
                setComment(new examCrudController().getAllExam());
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

            btnSave.setDisable(false);
            storeValidations();
        }

        public void btnSave (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            Exam exam = new Exam(ExamID.getText(), userID.getText(), Date.getValue().toString(), examtime.getValue().toString());
            if (new examCrudController().saveExam(exam)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save").show();
                ExamID.clear();
                userID.clear();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
            }
        }

        public void dtnupdate (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            Exam exam = new Exam(ExamID.getText(),
                    userID.getText(),
                    Date.getValue()
            );

            if (new examCrudController().updateExam(exam)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update...").show();
                tbrlExam.getItems().clear();
                setComment(new examCrudController().getAllExam());
                ExamID.clear();
                userID.clear();


            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...").show();
            }
        }







    public void btndelete(ActionEvent actionEvent) {
    }

    public void btncancel(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)ExamContext .getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
    }

    public void textFeld_On_Key_Pressde(KeyEvent keyEvent) {
        Object response = UtillController.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }

    }

    public void setComment(ArrayList<Exam> comments) {
        comments.forEach(exam -> {
            obList.add(
                    new ExamTM(exam.getExamId(),exam.getUserId(),exam.getDate(),exam.getTime())
            );
        });
        tbrlExam.setItems(obList);
    }

    public void btnSeach(ActionEvent actionEvent) {
    }
}
