package Controller;

import Model.User;
import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class usve {


    public static boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO USER VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstm.setObject(1, user.getUserId());
        pstm.setObject(2, user.getFname());
        pstm.setObject(3, user.getLname());
        pstm.setObject(4, user.getDoB());
        pstm.setObject(5, user.getNIC());
        pstm.setObject(6, user.getTP1());

        pstm.setObject(7, user.getNo());
        pstm.setObject(8, user.getSTREET());
        pstm.setObject(9, user.getCITY());
        pstm.setObject(10, user.getEMAIL());
        pstm.setObject(11, user.getType());
        pstm.setObject(12, user.getGEN());

        pstm.setObject(13, 1);
        pstm.setObject(14, user.getPASSWORD());

        return pstm.executeUpdate() > 0;
    }

    public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * from user ");
        ArrayList<User> users = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            users.add(
                    new User(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getString(6),
                            rst.getString(7),
                            rst.getString(8),
                            rst.getString(9),
                            rst.getString(10),
                            rst.getString(11),
                            rst.getString(12),
                            rst.getInt(13),
                            rst.getString(14)
                    )

            );

        }

        return users;
    }


}
