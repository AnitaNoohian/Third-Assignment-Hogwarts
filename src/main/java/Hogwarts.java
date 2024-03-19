import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Hogwarts {

    // TODO: Define Attributes
    public static List<Assistant> allAssistants = new ArrayList<>();
    public static HashMap<UUID, Teacher> allTeachers = new HashMap<>();
    public static HashMap<UUID, Student> allStudents = new HashMap<>();
    public static HashMap<UUID, Courses> allCourses = new HashMap<>();

    public Hogwarts(){

    }


    // TODO: Define Functionalities
    public void viewAllTeachers(Courses course) {

    }

    public void viewAllStudents(Student student) {

    }

    public void viewAllCourses(Teacher teacher) {

    }
    public void addCourses(Courses courses){
        allCourses.put(courses.getCourseID(),courses);
    }
    public void addStudents(Student student){
        allStudents.put(student.getAccountID(),student);
    }

}
