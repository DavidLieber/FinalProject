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

    public Course(String name) {
        this.name = name;
        this.questions = new ArrayList<Question>();
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public Course()
    {

    }
}