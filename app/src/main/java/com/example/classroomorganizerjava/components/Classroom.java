package com.example.classroomorganizerjava.components;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class Classroom {
    private Student[] students;

    public Classroom(){
        this.students = new Student[0];
    }
    public Classroom(Student[] students){
        this.students = students;
    }
    public Classroom(String filepath){
        this.loadCSV(filepath);
    }

    // TODO: return copy
    public Student[] getStudents(){
        return this.students;
    }

    // TODO: implement
    public void addStudent(Student s){
    }

    // TODO: implement
    public void removeStudent(Student s){
    }

    // TODO: implement
    public void loadCSV(String filepath){
    }

    // TODO: implement
    public void saveCSV(String filepath){
    }

    // TODO: implement
    public void saveCSV(){
    }

    @NonNull
    @Override
    public String toString() {
        return "Classroom{" +
                "students=" + Arrays.toString(students) +
                '}';
    }
}
