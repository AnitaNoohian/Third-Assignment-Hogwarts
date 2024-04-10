import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Courses {
     private String courseName;
     private String courseDetail;
     private UUID courseID;
     public HashMap<UUID,Student> students;
     public HashMap<UUID,Double> scores;
     private String teacherName;

     public Courses(String courseName, String courseDetail){
         this.courseDetail = courseDetail;
         this.courseName = courseName;
         this.students = new HashMap<>();
         this.courseID = UUID.randomUUID();
         this.scores = new HashMap<>();
     }

     public void setTeacherName(String name){
         this.teacherName = name;
     }
     public String getTeacherName(){
         return teacherName;
     }
     public void addStudent(Student student){
         students.put(student.getAccountID(),student);
     }
     public HashMap<UUID,String> getStudents(){
         HashMap<UUID,String> studentName = new HashMap<>();
         for (UUID key : students.keySet()){
             studentName.put(key,students.get(key).getUsername());
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
