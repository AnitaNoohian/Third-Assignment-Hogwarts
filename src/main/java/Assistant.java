import java.util.ArrayList;
import java.util.List;

public class Assistant extends Account{

    static List<Teacher> requests;
    public Assistant(String username, String password) {
        super(username, password);
        requests = new ArrayList<>();
    }

    public void removeStudent(Student student){
        Hogwarts.allStudents.remove(student.getAccountID());
    }
    public void removeTeacher(Teacher teacher){
        Hogwarts.allTeachers.remove(teacher.getAccountID());
    }
    public void makeCourses(){

    }

}
