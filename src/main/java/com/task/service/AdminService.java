package com.task.service;

import com.task.entity.*;
import com.task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdminService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentAddressRepository studentAddressRepository;
    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO admitStudent(StudentDTO studentDTO) {
        Student student = studentMapper.mapToEntity(studentDTO);
        student.getAddresses().forEach(address -> address.setStudent(student)); // setting the student for each address
        Student savedStudent = studentRepository.save(student);
        return studentMapper.mapToDTO(savedStudent);
    }


    public CourseDTO uploadCourseDetails(CourseDTO courseDTO) {
    Course course=studentMapper.mapToEntity(courseDTO);
    Course savedCourse= courseRepository.save(course);
    return studentMapper.mapToDTO(savedCourse);
    }

    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<StudentDTO> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        return students.stream().map(studentMapper::mapToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getStudents().stream().map(studentMapper::mapToDTO).collect(Collectors.toList());
    }

    }
