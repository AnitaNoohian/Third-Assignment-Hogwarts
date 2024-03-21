import java.util.*;

public class Teacher extends Account{

    List<Courses> courses;
    public Teacher(String username, String password) {
        super(username, password);
        courses = new ArrayList<>();
    }

    public void addCourse(Courses course){
        courses.add(course);
        course.setTeacherName(getUsername());
    }
    public void takeCourse(String courseName){
         for (UUID key : Hogwarts.allCourses.keySet()) {
             if (courseName.equals(Hogwarts.allCourses.get(key).getCourseName())) {
                 addCourse(Hogwarts.allCourses.get(key));
             }
         }
    }
    public HashMap<UUID, String> getCourse(){
        HashMap<UUID, String> coursesName = new HashMap<>();
        for(int i = 0; i < courses.size(); i++){
            coursesName.put(courses.get(i).getCourseID(), courses.get(i).getCourseName());
        }
        return coursesName;
    }
    public void scoreStudent(){
        System.out.println("Please Choose a Course(enter the number):");
        HashMap<UUID, String> coursesName = getCourse();
        int num = 1;
        for (String value :coursesName.values()) {
            System.out.println(num + ")" + value);
            num++;
        }
        int input;
        Scanner inputCourse = new Scanner(System.in);
        input = inputCourse.nextInt();
        List<UUID> keys = new ArrayList<>(coursesName.keySet());
        UUID course = keys.get(input-1);
        for (UUID key : Hogwarts.allCourses.keySet()) {
            HashMap<UUID,String> output = Hogwarts.allCourses.get(key).getStudents();
            if (key == course) {
                System.out.println("Please choose the student(enter the number):");
                num = 1;
                for (String value : output.values()){
                    System.out.println(num + ")" + value);
                    num++;
                }
            }
            Scanner inputName = new Scanner(System.in);
            input = inputName.nextInt();
            List<UUID> keys1 = new ArrayList<>(output.keySet());
            UUID student = keys1.get(input-1);
            System.out.println("Enter the score of the student:");
            double score;
            Scanner inputScore = new Scanner(System.in);
            score = inputScore.nextDouble();
            Hogwarts.allCourses.get(course).scores.put(student,score);
        }
    }
    public List<String> listofCourses(){
        List<String> coursesList = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++){
            coursesList.add(courses.get(i).getCourseName());
        }
        return coursesList;
    }

    public List<String> studentsInCourse(Courses course){
        List<String> students = new ArrayList<>();
        for (Student value : course.students.values()) {
            students.add(value.getUsername());
        }
        return students;
    }

    public void score(){

    }


}
