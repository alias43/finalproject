package org.binaracademy.finalproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.binaracademy.finalproject.DTO.CourseDTO;
import org.binaracademy.finalproject.model.Course;
import org.binaracademy.finalproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
        return ResponseEntity.ok()
                .body("Add new course successfully!");
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<CourseDTO>> getAllCourse(){
        return ResponseEntity.ok()
                .body(courseService.getAllCourse());
    }

    @DeleteMapping(value = "/delete/{codeCourse}")
    @Operation(summary = "Delete data at course_category first, after that delete course")
    public ResponseEntity<String> deleteCourse(@PathVariable String codeCourse){
        courseService.deleteCourseByCode(codeCourse);
        return ResponseEntity.ok().body("Course with code: " + codeCourse + " successfully deleted");
    }

    @PutMapping(value = "/update/{code}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String code,
                                                  @RequestBody Course course){
        return ResponseEntity.ok()
                .body(courseService.updateCourse(course,code));
    }
}