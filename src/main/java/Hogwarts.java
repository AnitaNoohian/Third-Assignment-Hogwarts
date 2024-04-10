import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Hogwarts {

    // TODO: Define Attributes
    public static HashMap<UUID, Assistant> allAssistants = new HashMap<>();
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
    public void rateTeacher(){

    }
    public static void addComment(Teacher teacher,String comment){
        teacher.comments.add(comment);
    }
    public static List<String> showComment(Teacher teacher){
        return teacher.comments;
    }
    public static void addRate(Teacher teacher, UUID uuid, int score){
        int rate = 0;
        if (teacher.rates.get(uuid) != null) {
            rate = teacher.rates.get(uuid);
        }
        rate += score;
        teacher.rates.put(uuid, rate/2);
    }
    public static Integer showRate(Teacher teacher, UUID uuid){
        return teacher.rates.get(uuid);
    }
}
