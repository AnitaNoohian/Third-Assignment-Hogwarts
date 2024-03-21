/*
    TODO: Import all the classes that you have defined and make use of them to build the program.
 */


import java.util.*;

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
        main:
        while (true) {
            System.out.println("Please enter your username:");
            Scanner input = new Scanner(System.in);
            String username = input.next();
            System.out.println("Please enter your password:");
            String password = input.next();
            for (Assistant value : Hogwarts.allAssistants.values()){
                if (username.equals(value.getUsername()) && value.validatePassword(password)){
                    System.out.println("You entered as a assistant!\n");
                    assistantFeature(value);
                    break main;
                }
            }
            if (username.equals(headMaster.getUsername()) && headMaster.validatePassword(password)) {
                System.out.println("You entered as a headmaster!\n");
                assistantFeature(headMaster);
                break;
            } else {
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
                    System.out.println("You want to add a new Assistant!");
                    System.out.println("Enter the username: ");
                    String name = input1.next();
                    System.out.println("Set a password for the user: ");
                    String password = input1.next();
                    Assistant newAssistant = new Assistant(name,password);
                    Hogwarts.allAssistants.put(newAssistant.getAccountID(),newAssistant);
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
                System.out.println("Which profile you want to remove?\n1.A Teacher\t2.A Student\t3.A Assistant");
                Scanner in = new Scanner(System.in);
                int remove = in.nextInt();
                if (remove == 1){
                    int num = 1;
                    for (Teacher value : Hogwarts.allTeachers.values()) {
                        System.out.print(num + ")" + value.getUsername());
                        num++;
                    }
                    int rem = in.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allTeachers.keySet());
                    UUID uuid = keys.get(rem-1);
                    String name = Hogwarts.allTeachers.get(uuid).getUsername();
                    Hogwarts.allTeachers.remove(uuid);
                    for (Teacher value : Hogwarts.allTeachers.values()){
                        if (uuid == value.getAccountID()){
                            Hogwarts.allTeachers.remove(uuid);
                        }
                    }
                    for (Student value : Hogwarts.allStudents.values()){
                        for (UUID key : value.teachers.keySet()){
                            if (key == uuid){
                                value.teachers.remove(uuid);
                            }
                        }
                        for (int i = 0; i < value.courses.size(); i++){
                            if (name.equals(value.courses.get(i).getCourseName())){
                                value.courses.get(i).setTeacherName(null);
                            }
                        }
                    }
                    for (Courses value : Hogwarts.allCourses.values()){
                        if (name.equals(value.getCourseName())){
                            value.setTeacherName(null);
                        }
                        for (Student value2 : value.students.values()){
                            for (UUID key : value2.teachers.keySet()){
                                if (key == uuid){
                                    value2.teachers.remove(uuid);
                                }
                            }
                            for (int i = 0; i < value2.courses.size(); i++){
                                if (name.equals(value2.courses.get(i).getCourseName())){
                                    value2.courses.get(i).setTeacherName(null);
                                }
                            }
                        }
                    }
                } else if (remove == 2){
                    int num = 1;
                    for (Student value : Hogwarts.allStudents.values()) {
                        System.out.print(num + ")" + value.getUsername());
                        num++;
                    }
                    int rem = in.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allStudents.keySet());
                    UUID uuid = keys.get(rem - 1);
                    String name = Hogwarts.allStudents.get(uuid).getUsername();
                    Hogwarts.allStudents.remove(uuid);
                    for (Teacher value : Hogwarts.allTeachers.values()) {
                        for (int i = 0; i < value.courses.size(); i++) {
                            for (UUID key : value.courses.get(i).scores.keySet()) {
                                if (uuid == key) {
                                    value.courses.get(i).scores.remove(uuid);
                                }
                            }
                            for (UUID key : value.courses.get(i).students.keySet()) {
                                if (key == uuid) {
                                    value.courses.get(i).students.remove(uuid);
                                }
                            }
                        }
                    }
                    for (Student value : Hogwarts.allStudents.values()) {
                        for (int i = 0; i < value.courses.size(); i++) {
                            for (UUID key : value.courses.get(i).scores.keySet()) {
                                if (uuid == key) {
                                    value.courses.get(i).scores.remove(uuid);
                                }
                            }
                            for (UUID key : value.courses.get(i).students.keySet()) {
                                if (key == uuid) {
                                    value.courses.get(i).students.remove(uuid);
                                }
                            }
                        }
                    }
                    for (Courses value : Hogwarts.allCourses.values()) {
                        for (UUID key : value.scores.keySet()) {
                            if (uuid == key) {
                                value.scores.remove(uuid);
                            }
                        }
                        for (UUID key : value.students.keySet()) {
                            if (key == uuid) {
                                value.students.remove(uuid);
                            }
                        }
                    }
                } else {
                    int num = 1;
                    for (Assistant value : Hogwarts.allAssistants.values()) {
                        System.out.print(num + ")" + value.getUsername());
                        num++;
                    }
                    int rem = in.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allAssistants.keySet());
                    UUID uuid = keys.get(rem - 1);
                    Hogwarts.allAssistants.remove(uuid);
                }
            } else if (choose == 4) {
                System.out.println("Which profile do you want to see?\n1.A Teacher\t2.A Student");
                Scanner in = new Scanner(System.in);
                int pro = in.nextInt();
                if (pro == 1){
                    System.out.println("Choose one teacher:(enter a number)");
                    int num = 1;
                    for (Teacher value : Hogwarts.allTeachers.values()) {
                        System.out.print((num+1) + ")" + value.getUsername());
                    }
                    int pro1 = in.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allTeachers.keySet());
                    UUID uuid = keys.get(pro1-1);
                    Teacher teacher = Hogwarts.allTeachers.get(uuid);
                    System.out.println("There is the information about \"" + teacher.getUsername() + "\" :");
                    System.out.println("ID : " + teacher.getAccountID());
                    System.out.println("The classes he/she is the teacher of: " + teacher.getCourse());
                } else {
                    System.out.println("Choose one Student:(enter a number)");
                    int num = 1;
                    for (Student value : Hogwarts.allStudents.values()) {
                        System.out.print((num+1) + ")" + value.getUsername());
                    }
                    int pro1 = in.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allTeachers.keySet());
                    UUID uuid = keys.get(pro1-1);
                    Student student = Hogwarts.allStudents.get(uuid);
                    System.out.println("There is the information about \"" + student.getUsername() + "\" :");
                    System.out.println("ID : " + student.getAccountID());
                    System.out.println("The Teachers he/she has courses with: " + student.getTeachers());
                }
            } else if (choose == 5) {
                List<String> courses = new ArrayList<>();
                List<String> details = new ArrayList<>();
                List<String> teachers = new ArrayList<>();
                List<String> studentsName = new ArrayList<>();

                for (UUID key : Hogwarts.allCourses.keySet()){
                    courses.add(Hogwarts.allCourses.get(key).getCourseName());
                    details.add(Hogwarts.allCourses.get(key).getCourseDetail());
                    teachers.add(Hogwarts.allCourses.get(key).getTeacherName());
                    HashMap<UUID,String> students = Hogwarts.allCourses.get(key).getStudents();
                    for (String value : students.values()) {
                        studentsName.add(value);
                    }
                }
                for (int i = 1; i <= Hogwarts.allCourses.size(); i++){
                    System.out.println(i + ")" + courses.get(i-1) + ": " + details.get(i-1));
                    if (teachers.get(i-1) != null){
                        System.out.println("The teacher who presents the lesson is \"" + teachers.get(i-1) + "\"");
                    }
                    if (studentsName.get(i-1) != null){
                        System.out.print("The list of students who takes this course is \"");
                        for (int j = 0; j < studentsName.size(); j++){
                            if (j != studentsName.size() - 1) {
                                System.out.print(studentsName.get(j));
                            } else {
                                System.out.print(studentsName.get(j) + ",");
                            }
                        }
                        System.out.println("\"");
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
                if (Hogwarts.allCourses.isEmpty()){
                    System.out.println("The list is empty!\n");
                } else {
                    System.out.println("Choose the course:(enter the number of the course)");
                    int num = 1;
                    for (UUID key : Hogwarts.allCourses.keySet()) {
                        System.out.println(num + ")" + Hogwarts.allCourses.get(key).getCourseName());
                        num++;
                    }
                    int course = input.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allCourses.keySet());
                    UUID in = keys.get(course - 1);
                    teacher.takeCourse(Hogwarts.allCourses.get(in).getCourseName());
                }
            } else if (choose == 2){
                List<String> output = teacher.listofCourses();
                if (output.isEmpty()){
                    System.out.println("The list is empty!\n");
                } else {
                    System.out.println(output);
                }
            } else if (choose == 3){
                teacher.scoreStudent();
            } else if (choose == 4){

            } else if (choose == 5){
                System.out.println("Username : " + teacher.getUsername());
                System.out.println("1.Change username\t2.Change Password");
                int in = input.nextInt();
                if (in == 1){
                    String saveUser = teacher.getUsername();
                    System.out.println("Enter your new username:");
                    String newUser = input.next();
                    teacher.changeUsername(newUser);
                    UUID user = teacher.getAccountID();
                    for (UUID key : Hogwarts.allTeachers.keySet()){
                        if (key == user){
                            Hogwarts.allTeachers.get(key).changeUsername(newUser);
                        }
                    }
                    for (Courses value : Hogwarts.allCourses.values()){
                        if (saveUser.equals(value.getCourseName())){
                            value.setTeacherName(newUser);
                        }
                        for (Student value1 : value.students.values()){
                            for (UUID key : value1.teachers.keySet()){
                                if (key == user){
                                    value1.teachers.get(key).replace(saveUser,newUser);
                                }
                            }
                        }
                    }
                    for (Student value : Hogwarts.allStudents.values()){
                        for (UUID key : value.teachers.keySet()){
                            if (key == user){
                                value.teachers.get(key).replace(saveUser,newUser);
                            }
                        }
                    }

                } else if (in == 2){
                    System.out.println("Entered your new password:");
                    String newPass = input.next();
                    teacher.changePassword(newPass);
                } else {
                    System.out.println("You entered the wrong number!\n");
                }
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
            System.out.println("What do you want to do?\n1.Take Course\t2.My Courses\t3.My Teachers\t4.Quiz\n" +
                    "5.My score\t6.Setting\t7.Log out");
            Scanner input = new Scanner(System.in);
            int choose = input.nextInt();
            if (choose == 1) {
                if (Hogwarts.allCourses.isEmpty()){
                    System.out.println("The list is empty!\n");
                } else {
                    System.out.println("Choose the course:(enter the number of the course)");
                    int num = 1;
                    for (UUID key : Hogwarts.allCourses.keySet()) {
                        System.out.println(num + ")" + Hogwarts.allCourses.get(key).getCourseName());
                        num++;
                    }
                    int course = input.nextInt();
                    List<UUID> keys = new ArrayList<>(Hogwarts.allCourses.keySet());
                    UUID in = keys.get(course - 1);
                    for (int i = 0; i < student.courses.size(); i++){
                        if (in == student.courses.get(i).getCourseID()){
                            System.out.println("You already take this course!");
                        } else {
                            student.addCourse(Hogwarts.allCourses.get(in), student);
                        }
                    }

                }
            } else if (choose == 2) {
                List<String> output = student.getCourses();
                if (output.isEmpty()){
                    System.out.println("The list is empty!\n");
                } else {
                    System.out.println(output);
                }
            } else if (choose == 3) {
                List<String> output = student.getTeachers();
                if (output.isEmpty()){
                    System.out.println("The list is empty!\n");
                } else {
                    System.out.println(output);
                }
            } else if (choose == 4) {
                student.quiz();
            } else if (choose == 5) {
                System.out.println("Which course's score do you want to see?(enter the number of course)");
                int num = 1;
                for (int i = 0; i < student.getCourses().size(); i++){
                    System.out.println(num + ")" + student.getCourses().get(i));
                }
                Scanner in = new Scanner(System.in);
                int course = in.nextInt();
                Courses name = student.courses.get(course-1);
                UUID uuid = student.courses.get(course-1).getCourseID();
                System.out.println(name.scores.get(uuid));
            } else if (choose == 6) {
                System.out.println("Username : " + student.getUsername());
                System.out.println("1.Change username\t2.Change Password");
                int in = input.nextInt();
                if (in == 1){
                    String saveUser = student.getUsername();
                    System.out.println("Enter your new username:");
                    String newUser = input.next();
                    student.changeUsername(newUser);
                    UUID user = student.getAccountID();
                    for (UUID key : Hogwarts.allStudents.keySet()){
                        if (key == user){
                            Hogwarts.allStudents.get(key).changeUsername(newUser);
                        }
                    }
                    for (Courses value : Hogwarts.allCourses.values()){
                        for (UUID key : value.students.keySet()) {
                            if (user == key){
                                value.students.get(key).changeUsername(newUser);
                            }
                        }
                    }
                    for (Student value : Hogwarts.allStudents.values()){
                        for (int i = 0; i < value.courses.size(); i++){
                            for (UUID key : value.courses.get(i).students.keySet()) {
                                if (user == key){
                                    value.courses.get(i).students.get(key).changeUsername(newUser);
                                }
                            }
                        }
                    }
                } else if (in == 2){
                    System.out.println("Entered your new password:");
                    String newPass = input.next();
                    student.changePassword(newPass);
                } else {
                    System.out.println("You entered the wrong number!\n");
                }
            } else if (choose == 7) {
                break;
            } else {
                System.out.println("You entered the wrong number!\n");
            }
        }
    }
}
