package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FogetpasswordController {
    public AnchorPane passContext;

    public void btncancel(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) passContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserLoginForm.fxml"))));

    }
}
