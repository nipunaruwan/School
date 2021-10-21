package Controller;

import Model.Course;
import Model.Library;
import Model.Timetable;
import db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimetableSaveController {
    public boolean saveTimetable(Timetable timetable) throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO timetable VALUE (?,?,?,?,?)");
        stm.setObject(1,timetable.getTimetableID());
        stm.setObject(2,timetable.getStartTime());
        stm.setObject(3,timetable.getEndTime());
        stm.setObject(4,timetable.getTeacherName());
        stm.setObject(5,timetable.getSubject());
        return stm.executeUpdate()>0;
    }
    public ArrayList<Timetable> getAllTimetables() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * from  Timetable");
        ArrayList<Timetable> Timetables = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Timetables.add(
                    new Timetable(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)

                    )

            );

        }

        return Timetables;
    }
}
