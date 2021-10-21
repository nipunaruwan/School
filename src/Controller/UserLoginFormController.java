package Controller;

import Util.UtillController;
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
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UserLoginFormController implements Initializable {
    public AnchorPane Usercontext;
    public Label lblE;
    public TextField UserName;
    public PasswordField Password;
    public Label lblError;
    public Label lbler;
    public Label loginpane;
    public Button btnSave;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern NamePattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern PasswordPattern = Pattern.compile("^[1-9]{3}");

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        LoginFormManager();

    }

    private void LoginFormManager() throws IOException {

        String user = UserName.getText();
        String pass = Password.getText();
      /*  if (UserName.getText().equals(user) && Password.getText().equals(password)) {
            Stage window = (Stage) Usercontext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
*/

        /*    System.out.println("wada");
        } else if (UserName.getText().isEmpty() && Password.getText().isEmpty()) {
            loginpane.setText("Your User Name Or Password IS Empty...!");
            UserName.clear();
            Password.clear();
        } else if (!UserName.getText().equals(user)) {
            loginpane.setText("Your User Name is incorrect..!");
            UserName.clear();
            Password.clear();
        } else if (!Password.getText().equals(password)) {
            loginpane.setText("Your Password is incorrect..!");
            UserName.clear();
            Password.clear();
        }
*/
        if (user.equals("admin") && pass.equals("1234")) {
            Stage window = (Stage) Usercontext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
            window.setTitle("Welcome Screen");
            window.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Try Again...").show();
        }

    }



    public void Loginkeypress(KeyEvent keyEvent) {
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
        storeValidations();

        btnSave.setDisable(false);

    }

    private void storeValidations() {
        map.put(UserName, NamePattern);
        map.put(Password, PasswordPattern);
    }

    public void btnpassword(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) Usercontext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Fogetpassword.fxml"))));
    }
}