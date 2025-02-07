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
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentAddressRepository studentAddressRepository;

    @Mock
    private StudentMapper studentMapper; 

    @InjectMocks
    private StudentService studentService;
  
  @Test
    void updateStudentProfile_shouldUpdateStudent() {
      Student student = new Student();
      student.setId(1L);
      student.setEmail("tharun12@email.com");

      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setEmail("tharun57@email.com");

      when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
      when(studentRepository.save(any(Student.class))).thenReturn(student);
      when(studentMapper.mapToDTO(any(Student.class))).thenReturn(studentDto);

      StudentDto updatedStudent = adminService.updateStudentProfile(1L, studentDto);

      assertEquals("tharun57@email.com", updatedStudent.getEmail());
      verify(studentRepository, times(1)).save(student);
    }

    @Test
    void searchCourses_shouldReturnMatchingCourses() {
        Student student = new Student();
        student.setId(1L);
        Course course1 = new Course();
        course1.setCourseName("Java");
        Course course2 = new Course();
        course2.setCourseName("Spring");
        student.setCourses(List.of(course1, course2));

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        List<Course> courses = studentService.searchCourses(1L, "Java");

        assertEquals(1, courses.size());
        assertEquals("Java", courses.get(0).getCourseName());
    }

    @Test
    void leaveCourse_shouldRemoveCourseFromStudent() {
        Student student = new Student();
        student.setId(1L);
        Course course = new Course();
        course.setId(1L);
        student.setCourses(List.of(course));

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        adminService.leaveCourse(1L, 1L);

        assertTrue(student.getCourses().isEmpty());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void getStudentByUniqueCodeAndDob_shouldReturnStudentDTO() {
      Student student = new Student();
      student.setUniqueStudentCode("testCode");
      student.setDob("2000-01-01");
      StudentDTO studentDTO= new StudentDTO();
      studentDto.setUniqueStudentCode("testCode");
      studentDto.setDob("2000-01-01");

      when(studentRepository.findByUniqueStudentCode("testCode")).thenReturn(student);
      when(studentMapper.mapToDto(student)).thenReturn(studentDto);

      StudentDTO result = studentService.getStudentByUniqueCodeAndDob("testCode", "2000-01-01");

      assertNotNull(result);
      assertEquals("testCode", result.getUniqueStudentCode());
    }
}
