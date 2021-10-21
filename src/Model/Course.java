package Model;

public class Course {
    String CourseID;
    String Coursename;
    String CourseType;

    public Course() {
    }

    public Course(String courseID, String coursename, String courseType) {
       this. CourseID = courseID;
     this. Coursename = coursename;
     this. CourseType = courseType;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getCoursename() {
        return Coursename;
    }

    public void setCoursename(String coursename) {
        Coursename = coursename;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseID='" + CourseID + '\'' +
                ", Coursename='" + Coursename + '\'' +
                ", CourseType='" + CourseType + '\'' +
                '}';
    }
}
