package org.example.university.repository;

import org.example.university.entity.Professor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends UserRepository<Professor>{
    Optional<Professor> findByCode(int code);
}
