package org.example;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "question_generator")
    @GenericGenerator(name = "question_generator", strategy = "increment")
    private int id;
    @Column(name = "question_text")
    private String text;
    @Column(name = "question_answer1")
    private String answer1;
    @Column(name = "question_answer2")
    private String answer2;
    @Column(name = "question_answer3")
    private String answer3;
    @Column(name = "question_answer4")
    private String answer4;
    private int correct_answer;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "question_subject",
            joinColumns = { @JoinColumn(name = "question_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
    private String question_code_number;


    public Question(String text, String answer1, String answer2, String answer3, String answer4, int correct_answer, Subject subject) {
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct_answer = correct_answer;
        this.subject = subject;
        subject.addQuestion(this);
        this.courses = new ArrayList<Course>();
        String first_part = String.valueOf(this.subject.getId());;
        String next_part = String.valueOf(this.id);;
        if (this.subject.getId() < 10)
            first_part = "0" + first_part;
        if (this.id < 10)
            next_part = "00" + next_part;
        else if (this.id < 100)
            next_part = "0" + next_part;
        this.question_code_number = first_part + next_part;
    }

    public void addCourse(Course course)
    {
        this.courses.add(course);
        course.addQuestion(this);
    }

    public Question()
    {

    }
}