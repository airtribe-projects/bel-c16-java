package org.example.airtribe.LearnerManagementSystemBelC16.repository;

import org.example.airtribe.LearnerManagementSystemBelC16.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
