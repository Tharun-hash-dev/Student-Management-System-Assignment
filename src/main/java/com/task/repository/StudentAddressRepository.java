package com.task.repository;

import com.task.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {

        List<StudentAddress> findByStudent(Student student);
}

