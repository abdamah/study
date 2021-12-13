package com.riigsoft.studybuddy.controller;

import com.riigsoft.studybuddy.entity.Course;
import com.riigsoft.studybuddy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;


    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("courses", service.getAllCourses());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("course", new Course());
        return "course/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Course course) {
        service.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping
    public String view(Model model) {
        model.addAttribute("totalFee", service.getTotalFees());
        model.addAttribute("courses", service.getAllCourses());
        return "course/view";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("course", service.getOneCourse(id));

        return "course/add";
    }

    @GetMapping("/delete")
    public String remove(@RequestParam() Long id, Model model) {
        if (id.toString().isEmpty())
            return "course/add";

        if (service.isCourseExist(id)) {
            if (service.getOneCourse(id).getStudents().size() == 0) {
                service.deleteCourse(id);
                model.addAttribute("message", "Successfully deleted specified course");
            } else {

                model.addAttribute("message", "Failed to delete,  Please delete the students associated with this course");
            }
        } else {
            model.addAttribute("message", "No Courses Found");
        }
        view(model);
        return "course/view";

    }

}
