package org.example;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "course_generator")
    @GenericGenerator(name = "course_generator", strategy = "increment")
    private int id;
    @Column(name = "course_name")
    private String name;
    @ManyToMany(mappedBy = "courses")
    private List<Question> questions;

    @OneToMany(mappedBy="course")
    private List<Exam> exams;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject_id;

    public Course(String name, Subject subject) {
        this.name = name;
        this.questions = new ArrayList<Question>();
        this.exams = new ArrayList<Exam>();
        this.subject_id = subject;
        subject.addCourse(this);
    }

    public List<Exam> getExams() {
        return exams;
    }



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
//        question.addCourse(this);            //this is specifically commented in order not to create a circle
    }

    public void addExam(Exam exam)
    {
        this.exams.add(exam);
    }

    public Subject getSubject_id() {
        return subject_id;
    }

    public Course()
    {

    }

    public List<Exam> getExam() {
        return exams;
    }


    @Override
    public String toString() {
        return this.name;
    }
}