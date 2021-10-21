package Controller;

import Model.BookType;
import Model.Library;
import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Lbsv {

    public boolean saveBookType(BookType bookType) throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO BOOKTYPE " +
                "VALUES (?,?,?)");
        stm.setInt(1,bookType.getBookTypeID());
        stm.setString(2,bookType.getTypeName());
        stm.setString(3,bookType.getStatus());
        return stm.executeUpdate()>0;
    }


    public List<String> getBookType() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM BOOKS");
        List<String> bookTypes=new ArrayList<>();
        ResultSet rst=stm.executeQuery();
        while (rst.next()){
            bookTypes.add(rst.getString(1));
        }
        return bookTypes;
    }

    public boolean Addbook(Library lib) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="insert into BOOKS Values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setObject(1,lib.getIDBOOk());
        preparedStatement.setObject(2,lib.getNAME());
        preparedStatement.setObject(3,lib.getAUTOR());
        preparedStatement.setObject(4,lib.getSTATUS());
        preparedStatement.setObject(5,lib.getREGDATE());

        preparedStatement.setObject(6,lib.getBookTypeID());


        return preparedStatement.executeUpdate()>0;

    }
    public ArrayList<Library> getAllBooks() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From BOOKS");
        ArrayList<Library> books = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            books.add(
                    new Library(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getInt(6)




                    ));
        }
        return books;
    }

}


