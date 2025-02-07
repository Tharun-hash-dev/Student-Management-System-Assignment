package com.task.validationsandConfigurations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest; 
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.task.entity.*;
import com.task.service.*;
import com.task.repository.*;
import com.task.controller.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest 
public class AdminServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentAddressRepository studentAddressRepository;

    @Mock
    private StudentMapper studentMapper; 

    @InjectMocks
    private AdminService adminService;

    @Test
    void admitStudent_shouldSaveStudentAndReturnDTO() {
        StudentDTO  studentDTO = new StudentDTO();
        studentDTO.setName("Test Student");
        

        Student student = new Student();
        // Set properties of the student entity

        when(studentMapper.mapToEntity(any(StudentDTO.class))).thenReturn(student); 
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.mapToDTO(any(Student.class))).thenReturn(studentDTO); 
        StudentDTO result = adminService.admitStudent(studentDTO);

        assertNotNull(result);
        assertEquals("Test Student", result.getName()); 
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void uploadCourseDetails_shouldSaveCourseAndReturnCourse() {
        Course course = new Course();
        course.setCourseName("Test Course");
        

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course result = adminService.addCourse(course);

        assertNotNull(result);
        assertEquals("Test Course", result.getCourseName());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void assignCourseToStudent_shouldAssignCourse() {
        Student student = new Student();
        student.setId(1L);
        Course course = new Course();
        course.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        adminService.assignCourseToStudent(1L, 1L);

        assertTrue(student.getCourses().contains(course)); 
        verify(studentRepository, times(1)).save(student); 
    }

    @Test
    void getStudentsByName_shouldReturnMatchingStudents() {
        Student student1 = new Student();
        student1.setName("Test Student 1");
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setName("Test Student 1");
        Student student2 = new Student();
        student2.setName("Another Test Student");
        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setName("Another Test Student");

        when(studentRepository.findByNameContainingIgnoreCase("Test")).thenReturn(List.of(student1, student2));
        when(studentMapper.mapToDTO(student1)).thenReturn(studentDTO1);
        when(studentMapper.mapToDTO(student2)).thenReturn(studentDTO2);

        List<StudentDTO> students = adminService.getStudentsByName("Test");

        assertEquals(2, students.size());
        assertEquals("Test Student 1", students.get(0).getName());
        assertEquals("Another Test Student", students.get(1).getName());
    }
      }
