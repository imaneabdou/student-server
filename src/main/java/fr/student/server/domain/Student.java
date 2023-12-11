package fr.student.server.domain;

import jakarta.persistence.*;

@SequenceGenerator(
        name = "sequenceGenerator",
        sequenceName = "SEQ_STUDENTID",
        allocationSize = 1)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
    private Integer id;

    private String name;

    private Integer age;

    public Student() {}

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name +
                "', age=" + age +
                '}';
    }
}