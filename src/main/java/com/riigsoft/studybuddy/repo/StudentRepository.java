package com.riigsoft.studybuddy.repo;

import com.riigsoft.studybuddy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);


}
