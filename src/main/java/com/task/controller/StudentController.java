package com.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.task.entity.*;
import com.task.repository.*;
import com.task.service.*;



@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    
  @PutMapping("/students/{studentId}") // Student updates profile
    public ResponseEntity<StudentDTO> updateStudentProfile(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudentProfile(studentId, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/students/{studentId}/courses/search")
    public ResponseEntity<List<Course>> searchCourses(@PathVariable Long studentId, @RequestParam String keyword) {
        List<Course> courses = studentService.searchCourses(studentId, keyword);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> leaveCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.leaveCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/students/authenticate") // Student authentication
    public ResponseEntity<StudentDTO> authenticateStudent(@RequestParam String uniqueCode, @RequestParam String dateOfBirth) {
      StudentDTO student = studentService.getStudentByUniqueCodeAndDob(uniqueCode, dateOfBirth);
      if (student != null) {
        return ResponseEntity.ok(student);
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
      }
    }
}


