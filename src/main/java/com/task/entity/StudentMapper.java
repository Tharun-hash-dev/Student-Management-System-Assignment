package com.task.entity
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO mapToDTO(Student student);
    Student mapToEntity(StudentDTO studentDTO);

    StudentAddressDTO mapToDTO(StudentAddress studentAddress);
    StudentAddress mapToEntity(StudentAddressDTO studentAddressDTO);

   CourseDTO mapToDTO(Course course);
    Course mapToEntity(CourseDTO courseDTO);

}
