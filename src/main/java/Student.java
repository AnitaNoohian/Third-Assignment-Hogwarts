import java.util.*;

public class Student extends Account {

    public String hogwartGroup;
    List<Courses> courses;
    HashMap<UUID,String> teachers;
    public Student(String username, String password) {
        super(username, password);
        courses = new ArrayList<>();
        teachers = new HashMap<>();
    }

    public void addCourse(Courses course, Student student){
        courses.add(course);
        course.addStudent(student);
        teachers.put(student.getAccountID(),course.getTeacherName());
    }
    public List<String> getCourses(){
        List<String> coursesName = new ArrayList<>();
        for(int i = 0; i < courses.size(); i++){
            coursesName.add(courses.get(i).getCourseName());
        }
        return coursesName;
    }
    public List<String> getTeachers(){
        List<String> teacher = new ArrayList<>();
        for (String value : teachers.values()){
            teacher.add(value);
        }
        return teacher;
    }

    public void quiz(){
        Random random = new Random();
        int group = random.nextInt(4)+1;
        if (group == 1){
            hogwartGroup = "Gryffindor";
        } else if (group == 2) {
            hogwartGroup = "Hufflepuff";
        } else if (group == 3) {
            hogwartGroup = "Ravenclaw";
        } else {
            hogwartGroup = "Slytherin";
        }
        System.out.println("Your Hogwarts group is " + hogwartGroup + "!");
    }
}
