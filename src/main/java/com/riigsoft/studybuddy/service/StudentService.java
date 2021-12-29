package com.riigsoft.studybuddy.service;

import com.riigsoft.studybuddy.entity.Student;

import java.util.List;

public interface StudentService {
    Long saveStudent(Student student);

    void updateStudent(Long courseId, Student student);

    void deleteStudent(Long id);

    Student getOneStudent(Long id);

    Student getStudentByEmail(String email);

    List<Student> getAllStudents();



}
