package view.tm;

public class StudentTm {
    private String StudentID;
    private String StudentName;
    private String Address;
    private String Email;
    private String ContactNo;
    private String City;
    private String CourseID;
    private String Date;

    public StudentTm(String studentID, String studentName, String address, String email, String contactNo, String city) {
    }

    public StudentTm(String studentID, String studentName, String address, String email, String contactNo, String city, String courseID, String date) {
        StudentID = studentID;
        StudentName = studentName;
        Address = address;
        Email = email;
        ContactNo = contactNo;
        City = city;
        CourseID = courseID;
        Date = date;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentID='" + StudentID + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", ContactNo='" + ContactNo + '\'' +
                ", City='" + City + '\'' +
                ", CourseID='" + CourseID + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
