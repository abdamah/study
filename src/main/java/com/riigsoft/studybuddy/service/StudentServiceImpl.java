package com.riigsoft.studybuddy.service;

import com.riigsoft.studybuddy.entity.Student;
import com.riigsoft.studybuddy.exception.StudentNotFoundException;
import com.riigsoft.studybuddy.repo.CourseRepository;
import com.riigsoft.studybuddy.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository repo;
    private CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository repo, CourseRepository courseRepository) {
        this.repo = repo;
        this.courseRepository = courseRepository;
    }

    @Override
    public Long saveStudent(Student student) {

        return repo.save(student).getId();
    }

    @Override
    public void updateStudent(Long courseId, Student student) {

    }

    @Override
    public void deleteStudent(Long id) {
        if (getOneStudent(id) != null)
            repo.deleteById(id);
    }


    @Override
    public Student getOneStudent(Long id) {
        if (repo.findById(id) == null)
            throw new StudentNotFoundException("Student with '" + id + "' not exist");

        return repo.findById(id).get();
    }

    @Override
    public Student getStudentByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }


}
