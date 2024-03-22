# HOGWART WEBSITE

## Description

it is like a website who teachers and students have account in it.   
in this website student can found a hogwarts group, rate a teacher and take courses.  
Teachers could take courses ,scoring students and rating other teachers.

## The challenges I faced
in this code i faced lots of problems.   
My problems are divided into two parts : first is the concept of the features and second with the syntaxs.  
1.concept problems:
* for the items i want to make request first and the assistant should accept. example: take course or teacher's sign up
* In **change username** and **remove a user** i get confused because when a user change her/his name or when a assistant remove a user every part of the website where that account is located had to be changed.
* the connection between different classes.
* the concept of **ID** and how could i use it in code.


2.syntax problems:
* working with Hashmap; how to read the values or get keys when i needed or how to navigate for finding some information and etc. i read about hashmap functions like put(),get(),replace(),... to solve my problems and use hashmap.  
One thing I did wrong several times and got an error was that i forgot to new the hashmap i made.
* working with uuid to save my informations; uuid actually helped me in some parts of the code to find informations easier but saving information with id and read them correctly was sometimes hard for me in this code, and most of the time i used hashmap to save informations with id.
* find all the parts that a user's information saved and how to change or remove them when we should (The point I mentioned in the previous section too)  

After all this points i said and the partial problems i solved when coding, in some parts of coding i started in a irregular way and it makes my code dirty and hard to debug when i get some errors and i found that in such codes it can be helpful if you have a mental plan for the functions,classes and what you want to do before you started, so you can start coding in a better way.

## How does my code work
I made a headmaster (username:**Anita**, password:**1234**) to start a code with.
when you want to add a student, you should log in with the assistant and choose **create an account** to enter the student information and when you sign up a student with username and password, the student can enter the website with that particular user and pass and use all the features we have for students in the web.  
For log in as a teacher, first you should sign up. when you enter the information, the request send for the assistants and if one of the assistants accept the request, the teacher could log in with the user and pass that he/she sign up with.  
Only assistant can make a course and after they make it a student or teacher can get course in **take a course** part in their profiles. when they get a course, the request send to assistants and if one of them accept that the teacher will be a techer of the course or the student can have the course.
Just assistants can create a new assistant account and remove the users from website.
Both teachers and students can leave a comment and rate teachers.   
Teachers can grade students in the courses they teach and students can see their scores of each course in their profiles.  
Assistants can see the list of courses and the information about them like the teacher's name and the list of the students.
Assistants also can see the ID of the users in the **Show Profile** part of the web.