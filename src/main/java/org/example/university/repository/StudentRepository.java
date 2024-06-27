package org.example.university.repository;

import org.example.university.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends UserRepository<Student>{
    Optional<Student> findByStdNumber(long stdNumber);
}
