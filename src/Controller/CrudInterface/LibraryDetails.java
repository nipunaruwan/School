package Controller.CrudInterface;

import Model.Library;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryDetails {
    boolean saveLibrary(Library library) throws SQLException, ClassNotFoundException;
    boolean updateLibrary(Library library);
    boolean deleteLibrary(String id);
    Library getLibrary(String id);
    ArrayList<Library> getAllLibrary() throws SQLException, ClassNotFoundException;
}
