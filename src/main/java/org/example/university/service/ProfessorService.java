package org.example.university.service;

import lombok.AllArgsConstructor;
import org.example.university.entity.Course;
import org.example.university.entity.Professor;
import org.example.university.exception.ConflictException;
import org.example.university.exception.NotFoundException;
import org.example.university.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        Optional<Professor> optional;

        //It checks if this National Code already exists or not
        optional = professorRepository.findByNationalCode(professor.getNationalCode());
        if (optional.isPresent())
            throw new ConflictException("The professor with the desired National Code is available in the system.");

        optional = professorRepository.findByUsername(professor.getUsername());
        if (optional.isPresent())
            throw new ConflictException("The professor with the desired username is available in the system.");

        optional = professorRepository.findByCode(professor.getCode());
        if (optional.isPresent())
            throw new ConflictException("The professor with the desired code is available in the system.");

        return professorRepository.save(professor);
    }

    public Professor update(Professor professorUpdate) {
        Professor professor = findById(professorUpdate.getId());

        professorUpdate.setNationalCode(professor.getNationalCode());
        professorUpdate.setUsername(professor.getUsername());
        professorUpdate.setCode(professor.getCode());

        return professorRepository.save(professorUpdate);
    }

    public void deleteById(Long id) {
        findById(id);
        professorRepository.deleteById(id);
    }

    public Professor findById(Long id) {
        Optional<Professor> optional = professorRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("Professor Not found.");
        return optional.get();
    }

    public Professor findByCode(int code) {
        Optional<Professor> optional = professorRepository.findByCode(code);
        if (optional.isEmpty())
            throw new NotFoundException("Professor Not found.");
        return optional.get();
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public List<Course> listCoursesProfessor(int code) {
        Professor professor = findByCode(code);
        return professor.getCourses().stream().toList();
    }
}
