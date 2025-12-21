package org.example.airtribe.LearnerManagementSystemBelC16.controller;

import org.example.airtribe.LearnerManagementSystemBelC16.entity.Course;
import org.example.airtribe.LearnerManagementSystemBelC16.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {
  @Autowired
  private LearnerManagementService _learnerManagementService;


  @PostMapping("/courses")
  public Course createCourse(@RequestBody Course course) {
    return _learnerManagementService.createCourse(course);
  }
}
