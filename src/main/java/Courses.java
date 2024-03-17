import java.util.List;
import java.util.UUID;

public class Courses {
     private String courseName;
     private UUID courseID;
     private List<String> students;
     private String teacherName;

     public void setTeacherName(String name){
         this.teacherName = name;
     }
     public String getTeacherName(){
         return teacherName;
     }
     public void addStudent(String name){
         students.add(name);
     }
     public List<String> getStudents(){
         return students;
     }
     public String getCourseName(){
         return courseName;
     }
}
