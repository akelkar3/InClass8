package com.mad.homeworkgroup20.inclass8;

import java.io.Serializable;

/*Assignment no. 8
group: ankit kelkar, shubhra mishra*/

public class Student implements Serializable {
    String name;
    String email;
    String dept;
    String mood;

    public Student(String name, String email, String dept, String mood) {
        this.name = name;
        this.email = email;
        this.dept = dept;
        this.mood = mood;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
