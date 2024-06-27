package org.example.university.controller;

import lombok.RequiredArgsConstructor;
import org.example.university.dto.course.ViewCourseDTO;
import org.example.university.dto.student.AddStudentDTO;
import org.example.university.dto.student.UpdateStudentDTO;
import org.example.university.dto.student.ViewStudentDTO;
import org.example.university.entity.Course;
import org.example.university.entity.Student;
import org.example.university.mapper.CourseMapper;
import org.example.university.mapper.StudentMapper;
import org.example.university.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewStudentDTO save(@RequestBody AddStudentDTO addStudentDTO) {
        Student student = studentService.save(studentMapper.toEntity(addStudentDTO));
        return studentMapper.toViewDto(student);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ViewStudentDTO update(@RequestBody UpdateStudentDTO updateStudentDTO) {
        Student student = studentService.update(studentMapper.toEntity(updateStudentDTO));
        return studentMapper.toViewDto(student);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public ViewStudentDTO findById(@PathVariable Long id) {
        return studentMapper.toViewDto(studentService.findById(id));
    }

    @GetMapping("/list")
    public List<ViewStudentDTO> findAll() {
        return studentMapper.toListViewDTO(studentService.findAll());
    }

    @GetMapping("/{stdNumber}/course/list")
    public List<ViewCourseDTO> listCoursesStudent(@PathVariable long stdNumber) {
        List<Course> courses = studentService.listCoursesStudent(stdNumber);
        return courseMapper.toListViewDTO(courses);
    }
}
