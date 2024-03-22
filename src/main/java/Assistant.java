import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Assistant extends Account{

    static List<Student> student;
    static List<Courses> studentReq;
    static List<Teacher> requests;
    static List<Courses> teacherReq;
    static List<Teacher> teacher;
    public Assistant(String username, String password) {
        super(username, password);
        requests = new ArrayList<>();
        teacherReq = new ArrayList<>();
        teacher = new ArrayList<>();
        student = new ArrayList<>();
        studentReq = new ArrayList<>();
    }

}
