package view.tm;

public class TimetableTm {
    private String timetableID;
    private String StartTime;
    private String endTime;
    private String teacherName;
    private String subject;

    public TimetableTm() {
    }

    public TimetableTm(String timetableID, String startTime, String endTime, String teacherName, String subject) {
        this.timetableID = timetableID;
        StartTime = startTime;
        this.endTime = endTime;
        this.teacherName = teacherName;
        this.subject = subject;
    }

    public String getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(String timetableID) {
        this.timetableID = timetableID;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableID='" + timetableID + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
