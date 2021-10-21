package Controller;

import Model.Timetable;
import Util.UtillController;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.TimetableTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class timetableController implements Initializable {
    public TextField timetableID;
    public Button btntimeCancel;
    public AnchorPane timeContext;
    public TextField subject;
    public TextField Tname;
    public JFXTimePicker StarttimeContext;
    public JFXTimePicker endtimeContext;
    public TableView timetableT;
    public TableColumn timetableIDColum;
    public TableColumn StartTimeColom;
    public TableColumn endTimeColum;
    public TableColumn SubjectColum;
    public TableColumn teacherColum;
    public Button btnSave;
    ObservableList<TimetableTm> obList = FXCollections.observableArrayList();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(T0-)[0-9]{1,3}$");
    Pattern subjectPattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern namepattern = Pattern.compile("^[A-z]{3,40}$");
   /* Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");*/


    private void storeValidations() {
        map.put(timetableID, idPattern);
        map.put(subject, subjectPattern);
        map.put(Tname, namepattern);

    }

    public void initialize(URL location, ResourceBundle resources) {

        btnSave.setDisable(false);
        storeValidations();



        timetableIDColum.setCellValueFactory(new PropertyValueFactory<>("timetableID"));
        StartTimeColom.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        endTimeColum.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        teacherColum.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        SubjectColum.setCellValueFactory(new PropertyValueFactory<>("subject"));

        try {
            setComment(new TimetableSaveController().getAllTimetables());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnupdate(ActionEvent actionEvent) {
    }

    public void btnsave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Timetable timetable=new Timetable(timetableID.getText(),StarttimeContext.getValue().toString(),endtimeContext.getValue().toString(),Tname.getText(),subject.getText());
        if (new TimetableSaveController().saveTimetable(timetable)){
            new Alert(Alert.AlertType.CONFIRMATION,"Save sucessfull").show();

        }else {

                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
        }
    }

    public void btndelete(ActionEvent actionEvent) {

        }


    public void btnSearch(ActionEvent actionEvent) {
    }
    public void setComment(ArrayList<Timetable> comments) {
        comments.forEach(timetable -> {
            obList.add(
                    new TimetableTm(timetable.getTimetableID(),timetable.getStartTime(),timetable.getEndTime(),timetable.getTeacherName(),timetable.getSubject())
            );
        });
      timetableT.setItems(obList);
    }

    public void KeyPressTimeTable(KeyEvent keyEvent) {

        Object response = UtillController.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }

    }

}
