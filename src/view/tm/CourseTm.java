package view.tm;

public class CourseTm {

        private String CourseID;
        private String Coursename;
        private String CourseType;

        public CourseTm() {
        }

        public CourseTm(String courseID, String coursename, String courseType) {
            CourseID = courseID;
            Coursename = coursename;
            CourseType = courseType;
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
