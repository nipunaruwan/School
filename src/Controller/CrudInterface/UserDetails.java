package Controller.CrudInterface;

import Model.Exam;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDetails {
    boolean saveUser(User user) throws SQLException, ClassNotFoundException;
    boolean updateUser(User user);
    boolean deleteUser(String id);
    User getUser(String id);
    ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException;
}
