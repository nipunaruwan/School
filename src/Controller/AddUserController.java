package Controller;

import Model.User;
//import com.sun.deploy.net.MessageHeader;
import Util.UtillController;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddUserController implements Initializable {
    public AnchorPane AddContext;
    public Label uid;
    public TextField LName;
    public TextField CityID;
    public TextField FName;
    public TextField EmailID;
    public TextField TownID;
    public TextField TpNo1;
    public TextField NoID;
    public TextField TpNo2;
    public RadioButton male;
    public ComboBox Type;
    public DatePicker Dob;
    public TextField nic;
    public PasswordField password;
    public ComboBox grade;
    public Button btnSave;
    public ToggleGroup Gen;
    //private MessageHeader observableList;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(U0-)[0-9]{1,3}$");
    Pattern TownPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern CityPattern = Pattern.compile("^[A-z]{1,9}$");
    Pattern NamePattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern eMailPattern = Pattern.compile("^[a-z]{2,}(@)[a-z]{2,}(.com)$");
    Pattern NICPattern = Pattern.compile("^[0-9]{9,12}[v]?$");
    Pattern contactPattern = Pattern.compile("^([+94]?[0-9]{11}|((0)[0-9]{9}))$");
    Pattern noPattern = Pattern.compile("^[0-9]{4,}$");
    //Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
    Pattern PasswordPattern = Pattern.compile("^[A-z0-9]{8,}(@|#|$|%|^|&|!)$");


    private void storeValidations() {

        map.put(FName, NamePattern);
        map.put(LName, NamePattern);
        map.put(EmailID, eMailPattern);
        map.put(NoID, noPattern);
        map.put(CityID, CityPattern);
        map.put(TownID, TownPattern);
        map.put(TpNo1, contactPattern);
        map.put(password, PasswordPattern);
        map.put(nic, NICPattern);


    }


    public void btnExit(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) AddContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
    }

    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {




            String gen;
            if (male.isSelected()) {
                gen = "male";

            } else {
                gen = "female";
            }

            String dob = Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            User u = new User();

            u.setUserId(uid.getText());
            u.setFname(FName.getText());
            u.setLname(LName.getText());
            u.setDoB(dob);
            u.setNIC(nic.getText());
            u.setTP1(TpNo1.getText());
            //u.setTp2(TpNo2.getText());
            u.setNIC(NoID.getText());
            u.setSTREET(TownID.getText());
            u.setCITY(CityID.getText());
            u.setEMAIL(EmailID.getText());
            u.setType((String) Type.getValue());
            u.setPASSWORD(password.getText());
            u.setGEN(gen);






            boolean isSave = usve.saveUser(u);
            if (isSave) {
                loadAllUsers(new usve().getAllUsers());


                new Alert(Alert.AlertType.INFORMATION, "Success").show();
                //event.loadData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }



           /* DbConnection.IUD("insert into USER values('"+uid.getText()+"','"+FName.getText()+"','"+LName.getText()+"','"+dob+"','"+nic.getText()+"','"+TpNo1.getText()+"'," +
                    "'"+TpNo2.getText()+"','"+NoID.getText()+"','"+TownID.getText()+"','"+CityID.getText()+"','"+EmailID.getText()+"','"+Type.getValue()+"','"+gen+"','1')");
            new Alert(Alert.AlertType.INFORMATION, "Success").show();*/

            loadUid();
            clearField();

        }



    private void loadAllUsers(ArrayList<User> users) {

    }


    private void clearField() {
        FName.setText("");
        LName.setText("");
        Dob.setValue(null);
        nic.setText("");
        TpNo1.setText("");
       // TpNo2.setText("");
        NoID.setText("");
        TownID.setText("");
        CityID.setText("");
        EmailID.setText("");
        loadType();

    }

    private void loadType() {
        Type.getItems().addAll("Student", "Teacher", "Admin");
    }

    private void loadgrade() {
        grade.getItems().addAll("grade 1", "grade 2", "grade 3", "grade 4", "grade 5", "grade 6", "grade 7", "grade 8", "grade 9", "grade 10", "grade11", "grade 12", "grade13");
    }


    public void btnUpdate(ActionEvent actionEvent) {


    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUid();
        loadType();
        loadgrade();

        btnSave.setDisable(false);
        storeValidations();

    }


    private void loadUid() {
        try {
            int c = 0;
            ResultSet rs = DbConnection.search("select count(USERID) AS x from user");
            if (rs.next()) {
                c = Integer.parseInt(rs.getString("x"));
                c++;
                uid.setText("User1" + c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UserAdd(KeyEvent keyEvent) {

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
