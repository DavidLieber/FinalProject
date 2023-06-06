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

    public Subject(String name) {
        this.name = name;
        this.questions = new ArrayList<Question>();
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

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
}
