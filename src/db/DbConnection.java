package db;

import java.sql.*;

public class DbConnection {

    private static DbConnection dbConnection;
    private static Connection connection;

    private static void DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMSYS", "root", "1234");
    }

    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection getConnection(){
        return this.connection;
    }





    private Connection checkCon() throws Exception{
        if(connection==null){
           DbConnection();
        }
        return connection;
    }
    public static ResultSet search(String query)throws Exception{
        if(connection==null){
            DbConnection();
        }
        return connection.createStatement().executeQuery(query);
    }

    public static void IUD(String query)throws Exception{
        if (connection==null){
            DbConnection();
        }
        connection.createStatement().executeUpdate(query);
    }



}


