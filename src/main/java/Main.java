/*
    TODO: Import all the classes that you have defined and make use of them to build the program.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static Assistant headMaster = new Assistant("Anita", "1234");     //The headmaster is the head of the website and
                                                                                        //could manage everything!
    public static void main(String[] args) {
        // TODO: Program starts from here
        runMenu();
        System.out.println("Thanks for using my website^^");
    }

    public static void runMenu() {
        // TODO: Menu will be shown here...
        System.out.println("Welcome to the Hogwarts:)\n");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Who are you?\n1.Assistant\t 2.Teacher\t3.Student\t4.Exit");
            int role = input.nextInt();
            if (role == 1) {
                assistantLogIn();
            } else if (role == 2) {
                teacherLogIn();
            } else if (role == 3) {
                studentLogIn();
            } else if (role == 4){
                break;
            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }

    public static void assistantLogIn(){
        while (true) {
            System.out.println("Please enter your username:");
            Scanner input = new Scanner(System.in);
            String username = input.next();
            System.out.println("Please enter your password:");
            String password = input.next();
            if (username.equals(headMaster.getUsername()) && headMaster.validatePassword(password)) {
                System.out.println("You entered as a headmaster!\n");
                assistantFeature(headMaster);
                break;
            }
            //else if(){

           // }
            else{
                System.out.println("You entered something wrong!\n");
            }
        }
    }
    public static void assistantFeature(Assistant assistant) {
        while (true){
            System.out.println("What do you want to do?\n1.create a new account\t2.Requests\t3.remove a user\n" +
                    "4.show profiles\t5.view courses\t6.create a course\t7.Log out");
            Scanner input = new Scanner(System.in);
            Scanner input1 = new Scanner(System.in);
            Scanner input2 = new Scanner(System.in);
            int choose = input.nextInt();
            if (choose == 1) {
                System.out.println("Which account you want to create?\n1.Assistant\t2.Student");
                int create = input.nextInt();
                if (create == 1){

                } else if (create == 2){
                    System.out.println("Enter the student username:");
                    String name = input1.next();
                    System.out.println("choose a password for the student:");
                    String password = input1.next();
                    Student student = new Student(name,password);
                    Hogwarts.allStudents.put(student.getAccountID(),student);
                } else {
                    System.out.println("You entered the wrong number!\n");
                }

            } else if (choose == 2) {
                for (int i = 0; i < Assistant.requests.size(); i++) {
                    System.out.println(i + 1 + ")" + Assistant.requests.get(i).getUsername());
                }
                if (!Assistant.requests.isEmpty()) {
                    System.out.println("Enter the number of the user you want to accept the request:");
                    int req = input.nextInt();
                    Hogwarts.allTeachers.put(Assistant.requests.get(req - 1).getAccountID(), Assistant.requests.get(req - 1));
                    Assistant.requests.remove(req - 1);
                }
                else {
                    System.out.println("There is no request.\n");
                }
            } else if (choose == 3) {

            } else if (choose == 4) {

            } else if (choose == 5) {
                List<String> courses = new ArrayList<>();
                List<String> details = new ArrayList<>();
                List<String> teachers = new ArrayList<>();
                for (UUID key : Hogwarts.allCourses.keySet()){
                    courses.add(Hogwarts.allCourses.get(key).getCourseName());
                    details.add(Hogwarts.allCourses.get(key).getCourseDetail());
                    teachers.add(Hogwarts.allCourses.get(key).getTeacherName());
                }
                for (int i = 1; i <= Hogwarts.allCourses.size(); i++){
                    System.out.println(i + ")" + courses.get(i-1) + ": " + details.get(i-1));
                    if (teachers.get(i-1) != null){
                        System.out.println("The teacher who presents the lesson is \"" + teachers.get(i-1) + "\"");
                    }
                }
                System.out.println("\n");
            } else if (choose == 6) {
                System.out.println("Enter the course name:");
                String name = input1.next();
                System.out.println("Write the details about your course:");
                String detail = input2.nextLine();
                Courses course = new Courses(name,detail);
                Hogwarts.allCourses.put(course.getCourseID(),course);
            } else if (choose == 7) {
                break;
            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }
    public static void teacherFeature(Teacher teacher){
        while (true){
            System.out.println("What do you want to do?\n1.Take Course\t2.My Courses\t3.Score Students" +
                    "\n4.My Score\t5.Setting\t6.Log out");
            Scanner input = new Scanner(System.in);
            int choose = input.nextInt();
            if (choose == 1){
                System.out.println("Choose the course:(enter the number of the course)");
                int num = 1;
                for (UUID key : Hogwarts.allCourses.keySet()) {
                    System.out.println(num + ")" + Hogwarts.allCourses.get(key).getCourseName());
                    num++;
                }
                int course = input.nextInt();
                List<UUID> keys = new ArrayList<>(Hogwarts.allCourses.keySet());
                UUID in = keys.get(course-1);
                teacher.takeCourse(Hogwarts.allCourses.get(in).getCourseName());
            } else if (choose == 2){
                teacher.listofCourses();
            } else if (choose == 3){
                teacher.scoreStudent();
            } else if (choose == 4){

            } else if (choose == 5){

            } else if (choose == 6){
                break;
            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }
    public static void teacherLogIn(){
        pass:
        while (true) {
            System.out.println("1.I already have an account(log in)\t2.I am new in this website(sign up)\n0.back to the menu");
            Scanner input = new Scanner(System.in);
            int in = input.nextInt();
            if (in == 1) {
                System.out.println("Please enter your username:");
                Scanner input1 = new Scanner(System.in);
                String username = input1.next();
                System.out.println("Please enter your password:");
                Scanner input2 = new Scanner(System.in);
                String password = input2.next();
                for (Teacher value : Hogwarts.allTeachers.values()) {
                    if (username.equals(value.getUsername()) && value.validatePassword(password)) {
                        teacherFeature(value);
                        break pass;
                    }
                }
                System.out.println("You have not registered!\n");
            } else if (in == 2) {
                System.out.println("Please enter your username:");
                Scanner input1 = new Scanner(System.in);
                String username = input1.next();
                System.out.println("Please enter your password:");
                Scanner input2 = new Scanner(System.in);
                String password = input2.next();
                Teacher obj = new Teacher(username, password);
                Assistant.requests.add(obj);
                System.out.println("The request send for the assistant. you should wait until the assistant accept your request.\n" +
                        "(if the assistant accept your request you could entered with this username and password) \n");
                break;
            } else if (in == 0){
                break;
            } else {
                System.out.println("You entered the wrong number!");
            }
        }
    }
    public static void studentLogIn(){
        main:
        while (true) {
            System.out.println("Hello Student :) Log in if the assistant make an account for you;\n1.Log in\t2.back to the menu");
            Scanner st = new Scanner(System.in);
            int in = st.nextInt();
            if (in == 1) {
                System.out.println("Your username:");
                String name = st.next();
                System.out.println("Your password:");
                String pass = st.next();
                for (UUID key : Hogwarts.allStudents.keySet()) {
                    if (name.equals(Hogwarts.allStudents.get(key).getUsername()) && Hogwarts.allStudents.get(key).validatePassword(pass)) {
                        Student student = Hogwarts.allStudents.get(key);
                        System.out.println("*You entered as a student*");
                        studentFeatures(student);
                        break main;
                    }
                }
                System.out.println("There is no such username and password in the hogwarts:(\n");
            } else if (in == 2) {

            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }
    public static void studentFeatures(Student student){
        while (true) {
            System.out.println("What do you want to do?\n1.Take Course\t2.My Courses\n" +
                    "3.My Teachers\t4.Quiz\t5.Setting\t6.Log out");
            Scanner input = new Scanner(System.in);
            int choose = input.nextInt();
            if (choose == 1) {
                System.out.println("Choose the course:(enter the number of the course)");
                int num = 1;
                for (UUID key : Hogwarts.allCourses.keySet()) {
                    System.out.println(num + ")" + Hogwarts.allCourses.get(key).getCourseName());
                    num++;
                }
                int course = input.nextInt();
                List<UUID> keys = new ArrayList<>(Hogwarts.allCourses.keySet());
                UUID in = keys.get(course-1);
                student.addCourse(Hogwarts.allCourses.get(in),student);
            } else if (choose == 2) {
                student.getCourses();
            } else if (choose == 3) {
                student.getTeachers();
            } else if (choose == 4) {

            } else if (choose == 5) {

            } else if (choose == 6) {
                break;
            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }
}
