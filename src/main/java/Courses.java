import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Courses {
     private String courseName;
     private String courseDetail;
     private UUID courseID;
     private List<Student> students;
     public static HashMap<String,Double> scores;
     private String teacherName;

     public Courses(String courseName, String courseDetail){
         this.courseDetail = courseDetail;
         this.courseName = courseName;
         courseID = UUID.randomUUID();
     }

     public void setTeacherName(String name){
         this.teacherName = name;
     }
     public String getTeacherName(){
         return teacherName;
     }
     public void addStudent(Student student){
         students.add(student);
     }
     public List<String> getStudents(){
         List<String> studentName = new ArrayList<>();
         for(int i = 0; i < students.size(); i++){
             studentName.add(students.get(i).getUsername());
         }
         return studentName;
     }
     public String getCourseName(){
         return courseName;
     }

     public UUID getCourseID(){
         return courseID;
     }
     public String getCourseDetail(){
         return courseDetail;
     }
}
