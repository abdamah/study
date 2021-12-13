package com.riigsoft.studybuddy.controller;

import com.riigsoft.studybuddy.entity.Course;
import com.riigsoft.studybuddy.entity.Student;
import com.riigsoft.studybuddy.service.CourseService;
import com.riigsoft.studybuddy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private CourseService courseService;


    private void commonUi(Model model) {
        model.addAttribute("courses", courseService.findCourseByIdAdnName());
    }

    @GetMapping
    public String index(Model model) {

        model.addAttribute("students", service.getAllStudents());
        return "index";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        commonUi(model);
        return "student/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {

        model.addAttribute("student", service.getOneStudent(id));
        commonUi(model);
        return "student/add";
    }


    @GetMapping("/students")
    public String viewStudent(Model model) {

        model.addAttribute("students", service.getAllStudents());
        return "student/view";
    }

    @GetMapping("/delete")
    public String remove(@RequestParam Long id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

    // add course to student form
    @GetMapping("/addStudentCourse/{id}")
    public String AddCourse(
            @PathVariable Long id,
            Model model
    ) {
        commonUi(model);
        model.addAttribute("student", service.getOneStudent(id));

        return "student/addCourse";
    }

    //Add Course
    @GetMapping("/{id}/courses")
    public String studentAddCourse(
            @PathVariable Long id,
            @RequestParam Long courseId,
            Model model
    ) {

        Course course = courseService.getOneCourse(courseId);
        Student student = service.getOneStudent(id);

        if (student != null) {
            if (!student.hasCourse(course)) {
                student.getCourses().add(course);
            }

            service.saveStudent(student);
            model.addAttribute("student", service.getOneStudent(id));
            model.addAttribute("courses", courseService.getAllCourses());
            return "redirect:/students";
        }
        
        return "redirect:/students";
    }


    @GetMapping("/view")
    public String getOne(Model model, @RequestParam Long id) {

        model.addAttribute("student", service.getOneStudent(id));
        return "student/viewStudent";
    }

}
