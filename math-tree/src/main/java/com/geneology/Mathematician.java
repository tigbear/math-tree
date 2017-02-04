package com.geneology;

import java.util.LinkedList;

/**
 * Created by tanya on 1/30/17.
 */
public class Mathematician {

    private int id;
    private String name;
    private String almaMater;
    private int year;
    private String flag;
    private LinkedList<Student> students;

    public Mathematician(int id, String name, String almaMater, int year, String flag) {
        this.id = id;
        this.name = name;
        this.almaMater = almaMater;
        this.year = year;
        this.flag = flag;
    }

    public void setStudents(LinkedList<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlmaMater() {
        return almaMater;
    }

    public int getYear() {
        return year;
    }

    public String getFlag() {
        return flag;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }
}
