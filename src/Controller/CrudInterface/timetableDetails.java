package Controller.CrudInterface;

import Model.Timetable;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface timetableDetails {
    boolean saveTimetable(Timetable timetable) throws SQLException, ClassNotFoundException;
    boolean updateTimetable(Timetable timetable);
    boolean deleteTimetable(String id);
    Timetable getTimetable(String id);
    ArrayList<User> getAllTimetable() throws SQLException, ClassNotFoundException;
}
