package com.task.controller;

import com.task.entity.*;
import com.task.repository.*;
import com.task.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/students")
    public ResponseEntity<StudentDTO> admitStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO admittedStudent = adminService.admitStudent(studentDTO);
        return ResponseEntity.ok(admittedStudent);
    }

    @PostMapping("/admin/courses")
    public ResponseEntity<Course> uploadCourseDetails(@RequestBody Course course) {
        Course addedCourse = adminService.uploadCourseDetails(course);
        return ResponseEntity.ok(addedCourse);
    }

    @PostMapping("/admin/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        adminService.assignCourseToStudent(studentId, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByName(@RequestParam String name) {
        List<StudentDTO> students = adminService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/admin/courses/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Long courseId) {
        List<StudentDTO> students = adminService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(students);
    }
}

