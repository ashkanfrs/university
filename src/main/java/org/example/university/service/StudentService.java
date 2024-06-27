package org.example.university.service;

import lombok.AllArgsConstructor;
import org.example.university.entity.Course;
import org.example.university.entity.Student;
import org.example.university.exception.ConflictException;
import org.example.university.exception.NotFoundException;
import org.example.university.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student save(Student student) {
        Optional<Student> optional;

        //It checks if this National Code already exists or not
        optional = studentRepository.findByNationalCode(student.getNationalCode());
        if (optional.isPresent())
            throw new ConflictException("The student with the desired National Code is available in the system.");

        optional = studentRepository.findByUsername(student.getUsername());
        if (optional.isPresent())
            throw new ConflictException("The student with the desired username is available in the system.");

        optional = studentRepository.findByStdNumber(student.getStdNumber());
        if (optional.isPresent())
            throw new ConflictException("The student with the desired StdNumber is available in the system.");

        return studentRepository.save(student);
    }

    public Student update(Student studentUpdate) {
        Student student = findById(studentUpdate.getId());

        studentUpdate.setNationalCode(student.getNationalCode());
        studentUpdate.setUsername(student.getUsername());
        studentUpdate.setStdNumber(student.getStdNumber());

        return studentRepository.save(studentUpdate);
    }

    public void deleteById(Long id) {
        findById(id);
        studentRepository.deleteById(id);
    }

    public Student findById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("Student Not found.");
        return optional.get();
    }

    public Student findByStdNumber(Long stdNumber) {
        Optional<Student> optional = studentRepository.findByStdNumber(stdNumber);
        if (optional.isEmpty())
            throw new NotFoundException("Student Not found.");
        return optional.get();
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<Course> listCoursesStudent(long stdNumber) {
        Student student = findByStdNumber(stdNumber);
        return student.getCourses().stream().toList();
    }

}
