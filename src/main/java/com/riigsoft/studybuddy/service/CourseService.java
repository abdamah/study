package com.riigsoft.studybuddy.service;

import com.riigsoft.studybuddy.entity.Course;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseService {
    Long saveCourse(Course course);


    void deleteCourse(Long id);

   boolean isCourseExist(Long id);

    Course getOneCourse(Long id);

    List<Course> findByFeeLessThan(Double fee);

    List<Course> getAllCourses();

    Map<Long, String> findCourseByIdAdnName();


    Double getTotalFees();

}
