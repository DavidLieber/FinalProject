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

    @OneToMany(mappedBy = "to_question")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "question_course",
            joinColumns = { @JoinColumn(name = "question_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;   // TODO: GUI people: make sure all courses connected to a question have the same subject
    private String question_code_number;
    @ManyToMany(mappedBy = "questions")
    private List<Exam> exams;

    @ManyToMany(mappedBy = "correctly_answered_questions")
    private List<Grade> correct_grades;

    @OneToMany(mappedBy = "question")
    private List<Exam_Question_points> points;

    public Question(String text, Answer ans1, Answer ans2, Answer ans3, Answer ans4, Subject subject, Course course) {
        this.text = text;
        this.answers = new ArrayList<Answer>();
        this.answers.add(ans1);
        ans1.setQuestion(this);
        this.answers.add(ans2);
        ans2.setQuestion(this);
        this.answers.add(ans3);
        ans3.setQuestion(this);
        this.answers.add(ans4);
        ans4.setQuestion(this);
        //this.correct_answer = correct_answer;
        this.subject = subject;   // TODO: Gui people, make sure you don't allow adding a course from a different subject
        subject.addQuestion(this);
        this.courses = new ArrayList<Course>();
        this.courses.add(course);
        course.addQuestion(this);

        this.question_code_number = "need to update- check appropriate function- updateCode()";
        this.exams = new ArrayList<Exam>();
        this.correct_grades = new ArrayList<Grade>();
        this.points = new ArrayList<Exam_Question_points>();
    }

    public void addPoints(Exam_Question_points points)
    {
        this.points.add(points);
    }

    public void addGrade(Grade grade)
    {
        this.correct_grades.add(grade);
    }

    public void addExam(Exam exam)
    {
        this.exams.add(exam);
    }

    public void addCourse(Course course)
    {
        this.courses.add(course);
        course.addQuestion(this);
    }

    public void addAnswer(Answer answer)
    {
        this.answers.add(answer);
        answer.setQuestion(this);
    }

    public int getId() {
        return id;
    }

    /*public int getCorrect_answer() {
        return correct_answer;
    }*/

    public List<Course> getCourses() {
        return courses;
    }

    public String getQuestion_code_number() {
        return question_code_number;
    }

    public String getText() {
        return text;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setCorrect_answer(int correct_answer) {
        /*this.correct_answer = correct_answer;*/
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setQuestion_code_number(String question_code_number) {
        this.question_code_number = question_code_number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        String courses = "Courses' Name: ";
        for (Course course : this.courses)
        {
            courses += course + ", ";
        }
        String all_answers = "All answers: ";
        Answer correct_answer = null;
        for (Answer answer : this.answers)
        {
            if (answer.getIs_correct())
                correct_answer = answer;
            all_answers += answer + ", ";
        }
        return "Subject: " + this.subject + "\nQuestion: " +
                this.text + "\n" + all_answers + "\nCorrect answer index: " +
                correct_answer + "\n" + courses +"\n";
    }

    public void updateCode()
    {
        String first_part = String.valueOf(this.subject.getId());
        String next_part = String.valueOf(this.id);
        if (this.subject.getId() < 10)
            first_part = "0" + first_part;
        if (this.id < 10)
            next_part = "00" + next_part;
        else if (this.id < 100)
            next_part = "0" + next_part;
        this.question_code_number = first_part + next_part;
    }

    public Question()
    {

    }
}