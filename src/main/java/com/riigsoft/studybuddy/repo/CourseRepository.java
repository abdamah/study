package com.riigsoft.studybuddy.repo;

import com.riigsoft.studybuddy.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT id, name FROM Course")
    List<Object[]> findCourseByIdAdnName();

    List<Course> findByFeeLessThan(Double fee);

    @Query(value = "SELECT sum(fee) FROM Course")
    public Double total();
}
