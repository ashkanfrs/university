package org.example.university.service;

import lombok.AllArgsConstructor;
import org.example.university.entity.Course;
import org.example.university.entity.Professor;
import org.example.university.entity.Student;
import org.example.university.exception.ConflictException;
import org.example.university.exception.NotFoundException;
import org.example.university.repository.CourseRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final ProfessorService professorService;

    public Course save(Course course) {
        if (courseRepository.findByCode(course.getCode()).isPresent())
            throw new ConflictException("The course with the desired code is available in the system.");
        return courseRepository.save(course);
    }

    public Course update(Course courseUpdate) {
        Course course = findById(courseUpdate.getId());
        courseUpdate.setCode(course.getCode());
        return courseRepository.save(courseUpdate);
    }

    @Cacheable(value = "course", key = "#id")
    public Course findById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("Course Not found.");
        return optional.get();
    }

    @Cacheable(value = "course", key = "result.id")
    public Course findByCode(int code) {
        Optional<Course> optional = courseRepository.findByCode(code);
        if (optional.isEmpty())
            throw new NotFoundException("Course Not found.");
        return optional.get();
    }

    @Caching(evict = {
            @CacheEvict(value = "allCourse", allEntries = true),
            @CacheEvict(value = "course", key = "#id")
    })
    public void deleteById(Long id) {
        findById(id);
        courseRepository.deleteById(id);
    }

    @Cacheable(value = "allCourse")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Caching(evict = {
            @CacheEvict(value = "allCourse", allEntries = true),
            @CacheEvict(value = "course", allEntries = true)
    })
    public void deleteAll() {
        courseRepository.deleteAll();
    }


    public void addStudent(int codeCourse, long stdNumber) {
        Student student = studentService.findByStdNumber(stdNumber);
        Course course = findByCode(codeCourse);

        course.getStudents().add(student);
        student.getCourses().add(course);

        studentService.update(student);
        update(course);
    }

    public void removeStudent(int codeCourse, long stdNumber) {
        Student student = studentService.findByStdNumber(stdNumber);
        Course course = findByCode(codeCourse);

        if (!course.getStudents().contains(student))
            throw new NotFoundException("The student does not have this course.");
        course.getStudents().remove(student);
        student.getCourses().remove(course);

        studentService.update(student);
        update(course);
    }

    public List<Student> listStudents(int codeCourse) {
        return findByCode(codeCourse).getStudents().stream().toList();
    }

    public void addProfessor(int codeCourse, int codeProfessor) {
        Professor professor = professorService.findByCode(codeProfessor);
        Course course = findByCode(codeCourse);

        course.setProfessor(professor);
        professor.getCourses().add(course);

        professorService.update(professor);
        update(course);
    }

    public void removeProfessor(int codeCourse) {
        Course course = findByCode(codeCourse);
        if (course.getProfessor() == null)
            throw new NotFoundException("The professor is not set for this course.");

        Professor professor = course.getProfessor();
        course.setProfessor(null);

        professor.getCourses().remove(course);

        professorService.update(professor);
        update(course);
    }

    public Professor getProfessor(int codeCourse) {
        Course course = findByCode(codeCourse);
        if (course.getProfessor() == null)
            throw new NotFoundException("The professor is not set for this course.");
        return course.getProfessor();
    }

}
