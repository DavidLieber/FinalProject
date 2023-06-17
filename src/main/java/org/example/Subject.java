package org.example;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "subject_generator")
    @GenericGenerator(name = "subject_generator", strategy = "increment")
    private int id;
    @Column(name = "subject_name")
    private String name;
    @OneToMany(mappedBy = "subject")
    private List<Question> questions;
//    @OneToMany(mappedBy="subject_id")
//    private List<Exam> exams;
    @OneToMany(mappedBy="subject_id")
    private List<Course> courses;
    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers;

    public Subject(String name) {
        this.name = name;
        this.questions = new ArrayList<Question>();
//        this.exams = new ArrayList<Exam>();
        this.courses = new ArrayList<Course>();
        this.teachers = new ArrayList<Teacher>();
    }

//    public List<Exam> getExams() {
//        return exams;
//    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addTeacher(Teacher teacher)
    {
        this.teachers.add(teacher);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addCourse(Course c)
    {
        this.courses.add(c);
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }
//    public void addExam(Exam exam)
//    {
//        this.exams.add(exam);
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject()
    {

    }

    @Override
    public String toString() {
        return this.name;
    }
}