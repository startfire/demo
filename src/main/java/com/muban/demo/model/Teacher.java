package com.muban.demo.model;

import lombok.Data;

import java.util.List;
@Data
public class Teacher {
    private String teacherName;
    private int teacherAge;
    private Course course;
    private List<Student> students;
}
