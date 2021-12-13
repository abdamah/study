package com.riigsoft.studybuddy.service;

import com.riigsoft.studybuddy.entity.Course;
import com.riigsoft.studybuddy.exception.CourseNotFoundException;
import com.riigsoft.studybuddy.repo.CourseRepository;
import com.riigsoft.studybuddy.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Long saveCourse(Course course) {
        return repo.save(course).getId();
    }


    @Override
    public void deleteCourse(Long id) {

        if (getOneCourse(id) != null)
            repo.deleteById(id);
    }

    @Override
    public Course getOneCourse(Long id) {
        if(repo.findById(id) == null)
            throw new CourseNotFoundException("Course with '"+ id +"' not exist");

        return repo.findById(id).get();
    }

    @Override
    public List<Course> findByFeeLessThan(Double fee) {
        return repo.findByFeeLessThan(fee);
    }

    @Override
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    @Override
    public Map<Long, String> findCourseByIdAdnName() {
        return AppUtil.convertListToMap(repo.findCourseByIdAdnName());
    }


    @Override
    public boolean isCourseExist(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Double getTotalFees() {

        return repo.total();
    }
}
