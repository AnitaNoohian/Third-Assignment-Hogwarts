import java.util.ArrayList;
import java.util.List;

public class Student extends Account {

    List<Courses> courses;
    List<String> teachers;
    public Student(String username, String password) {
        super(username, password);
        courses = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public void addCourse(Courses course, Student student){
        courses.add(course);
        course.addStudent(student);
        teachers.add(course.getTeacherName());
    }
    public List<String> getCourses(){
        List<String> coursesName = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            coursesName.add(courses.get(i).getCourseName());
        }
        return coursesName;
    }
    public List<String> getTeachers(){
        return teachers;
    }
}
