package com.geneology;

/**
 * Created by tanya on 2/3/17.
 */
public class Student implements Comparable {

    private int id;
    private String name;
    private String school;
    private int year;
    private int studentCount;

    public Student(int id, String name, String school, int year, int studentCount) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.year = year;
        this.studentCount = studentCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public int getYear() {
        return year;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int compareTo(Object object) {
        if (!(object instanceof Student)) {
            return 0;
        }
        Student otherStudent = (Student)object;
        // return this.id - otherStudent.id ; //result of this operation can overflow
        return (this.getId() < otherStudent.getId() ) ? -1: (this.getId() > otherStudent.getId()) ? 1:0 ;
    }
}
