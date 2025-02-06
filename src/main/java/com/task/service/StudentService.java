package com.task.service;

import com.task.entity.*;
import com.task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentAddressRepository studentAddressRepository;
    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO updateStudentProfile(Long studentId, StudentDTO studentDTO) {
    Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
    student.setEmail(studentDTO.getEmail());
    student.setMobileNumber(studentDTO.getMobileNumber());
    student.setParentsName(studentDTO.getParentsName());
    Student updatedStudent = studentRepository.save(student);
    return studentMapper.mapToDTO(updatedStudent);
    }

    public List<Course> searchCourses(Long studentId, String keyword) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getCourses().stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                        course.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        course.getTopics().stream().anyMatch(topic -> topic.toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().remove(course);
        studentRepository.save(student);
    }


    public StudentDTO getStudentByUniqueCodeAndDob(String uniqueCode, String dateOfBirth) {
      Student student = studentRepository.findByUniqueStudentCode(uniqueCode);
      if (student != null && student.getDob().equals(dateOfBirth)) {
        return studentMapper.mapToDTO(student);
      }
      return null; 
    }


}

