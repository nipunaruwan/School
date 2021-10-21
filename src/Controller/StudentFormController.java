package Controller;

import Model.User;
import view.tm.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {


    public AnchorPane UserContext;
    public TableView tableUser;
    public TableColumn UserID;
    public TableColumn Name;
    public TableColumn Tp;
    public TableColumn Type;
    public TableColumn Email;
    ObservableList<UserTm>observableList= FXCollections.observableArrayList();


    public void btnstudent(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) UserContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddUser.fxml"))));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       UserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        Tp.setCellValueFactory(new PropertyValueFactory<>("TP1"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Email.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));


        try {
          loadAllUsers(new usve().getAllUsers());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllUsers(ArrayList<User> users) {
        users.forEach(e -> {

            observableList.add(
                    new UserTm(
                            e.getUserId(),
                            e.getFname(),
                            e.getLname(),
                            e.getDoB(),
                            e.getNIC(),

                            e.getTP1(),
                            e.getNo(),
                            e.getSTREET(),
                            e.getCITY(),
                            e.getEMAIL(),
                            e.getType(),
                            e.getGEN(),
                            e.getSTATUS(),

                            e.getPASSWORD()

                    ));

        });

        tableUser.setItems(observableList);

    }
}


