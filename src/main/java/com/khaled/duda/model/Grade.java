package com.khaled.duda.model;

import javax.persistence.*;

@Entity
public class Grade {

    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Grade() {
    }

    public String getCouseName() {
        return couseName;
    }

    public void setCouseName(String couseName) {
        this.couseName = couseName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Grade(String couseName, int grade) {
        this.couseName = couseName;
        this.grade = grade;
    }

    public String couseName;
    public int grade;
}
