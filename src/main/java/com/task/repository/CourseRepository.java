package com.task.repository;

import com.task.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

        Course findByCourseName(String courseName);
}
