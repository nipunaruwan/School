package Controller;

import Model.BookType;
import Model.Library;
import Util.UtillController;
import db.DbConnection;
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
import view.tm.LibraryTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LibraryFormController implements Initializable {


    public TextField BookType;
    public TableColumn bookIDC;
    public TableColumn namec;
    public TableColumn status;
    public TableColumn autherc;
    public TableColumn regid;
    public ComboBox booktypeID;
    public TableView TableID;
    public TextField BooknameID;
    public TextField AutorID;
    public Label Date;
    public TextField Status;
    public Label BookID;
    public Label lbldate;
    public TextField txtBookTypeID;
    public TextField txtBookTypeName;
    public TextField txtStatus;
    public TextField txtAutorID;
    public TextField txtBookID;
    public TextField txtBookStatus;
    public TextField txtBookName;
    public TableView tblBooks;
    public TableColumn colbookIDC;
    public TableColumn colBookName;
    public TableColumn colDate;
    public TableColumn colStatus;
    public TableColumn colauther;
    public Button btnSave;
    public AnchorPane LibraryContext;
    public Button btnAdd;

    ObservableList<LibraryTm> observableList = FXCollections.observableArrayList();
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(BT0-)[0-9]{1,3}$");
    Pattern BookidPattern = Pattern.compile("^(B0-)[0-9]{1,3}$");
    Pattern StatusPattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern BStatusPattern = Pattern.compile("^[A-z]{3,40}$");
    Pattern CityPattern = Pattern.compile("^(C0-)[0-9]{1,3}$");

    //Pattern eMailPattern = Pattern.compile("^[A-z0-9]{3,40}[@][a-z]{2,}[.](com|lk|uk|[a-z]{2,})$");
  //  Pattern NICPattern = Pattern.compile("^(A-z0-9){8,12}$");
  //  Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    //Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
   // Pattern PasswordPattern = Pattern.compile("^[A-z0-9]{8,}(@|#|$|%|^|&|!)$");



    private void storeValidations() {


        map.put(txtBookTypeID, idPattern);
        map.put(txtStatus, StatusPattern);
        map.put(txtBookTypeName,BStatusPattern );
        map.put(txtBookID, BookidPattern);
        map.put(txtAutorID,StatusPattern);
        map.put(txtBookName, StatusPattern);
        map.put(txtBookStatus, BStatusPattern);







    }

    public void btnlibadd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            ResultSet rs = DbConnection.search("Select IDBOOKTYPE from BOOKTYPE where TYPE='" + booktypeID.getValue().toString() + "'");
            if (rs.next()) {
                int bokTpId = 0;
                Library library = new Library(BooknameID.getText(), namec.getText(), AutorID.getText(), "1", regid.getText(), Integer.parseInt(rs.getString("IDBOOKTYPE")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colbookIDC.setCellValueFactory(new PropertyValueFactory<>("IDBOOk"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("REGDATE"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("STATUS"));
        colauther.setCellValueFactory(new PropertyValueFactory<>("AUTOR"));


        try {
            loadBookTypes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadBooks(new Lbsv().getAllBooks());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        lbldate.setText(sdf.format(new Date()));

        btnSave.setDisable(false);
        btnAdd.setDisable(false);
        storeValidations();
    }

  /*  public void btnAddnew(ActionEvent actionEvent) {
        try {
            if (!BookType.getText().isEmpty()) {
                ResultSet rs = DbConnection.seaarch("select * from BOOKTYPE where Tyepe'" + BookType.getText() + "'");
                if (!rs.next()) {
                    DbConnection.IUD("insert into BOOKTYPE (TYPE,STATUS) VALUSE('" + BookType.getText() + "','1')");
                    BookType.setText("");
                } else {
                    System.out.println("The name has been already saved!!");
                }

            } else {
                System.out.println("fill the name ");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }*/


    public void loadBookTypes() throws Exception {
      //  PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM BOOKTYPE");

        ResultSet rst=DbConnection.search("SELECT * FROM BOOKTYPE");
        while (rst.next()){
            booktypeID.getItems().add(String.valueOf(rst.getInt(1)));
        }

    }


    public void addBookTypeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        BookType bookType=new BookType(Integer.parseInt(txtBookTypeID.getText()),txtBookTypeName.getText(),
                txtStatus.getText());

        if(new Lbsv().saveBookType(bookType)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Successfull").show();
            booktypeID.getItems().add(txtBookTypeID.getText());

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void bookSaveOnAction(ActionEvent actionEvent) throws Exception {
        Library lib=new Library(txtBookID.getText(),txtBookName.getText(),txtAutorID.getText(),txtBookStatus.getText(),
                lbldate.getText(),Integer.parseInt(booktypeID.getValue().toString()));

        if(new Lbsv().Addbook(lib)){
            loadBooks(new Lbsv().getAllBooks());
            new Alert(Alert.AlertType.CONFIRMATION, "Save Successfull").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
    public  void  loadBooks(ArrayList<Library>libraries){
        ObservableList<Library>observableList=FXCollections.observableArrayList();
        libraries.forEach(e->{observableList.add(new Library(e.getIDBOOk(),e.getNAME(),e.getAUTOR(),e.getSTATUS(),
                e.getREGDATE(),e.getBookTypeID()));});


        tblBooks.setItems(observableList);
    }

    public void btnlibcancel(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) LibraryContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Home.fxml"))));
    }

    public void Libkeyrelease(KeyEvent keyEvent) {
        Object response = UtillController.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}
