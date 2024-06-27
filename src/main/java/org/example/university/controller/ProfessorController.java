package org.example.university.controller;

import lombok.RequiredArgsConstructor;
import org.example.university.dto.course.ViewCourseDTO;
import org.example.university.dto.professor.AddProfessorDTO;
import org.example.university.dto.professor.UpdateProfessorDTO;
import org.example.university.dto.professor.ViewProfessorDTO;
import org.example.university.entity.Course;
import org.example.university.entity.Professor;
import org.example.university.mapper.CourseMapper;
import org.example.university.mapper.ProfessorMapper;
import org.example.university.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor/v1")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;
    private final ProfessorMapper professorMapper;
    private final CourseMapper courseMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewProfessorDTO save(@RequestBody AddProfessorDTO addProfessorDTO) {
        Professor professor = professorService.save(professorMapper.toEntity(addProfessorDTO));
        return professorMapper.toViewDto(professor);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ViewProfessorDTO update(@RequestBody UpdateProfessorDTO updateProfessorDTO) {
        Professor professor = professorService.save(professorMapper.toEntity(updateProfessorDTO));
        return professorMapper.toViewDto(professor);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        professorService.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public ViewProfessorDTO findById(@PathVariable Long id) {
        return professorMapper.toViewDto(professorService.findById(id));
    }

    @GetMapping("/list")
    public List<ViewProfessorDTO> findAll() {
        return professorMapper.toListViewDTO(professorService.findAll());
    }

    @GetMapping("/{codeProfessor}/course/list")
    public List<ViewCourseDTO> listCoursesProfessor(@PathVariable int codeProfessor) {
        List<Course> courses = professorService.listCoursesProfessor(codeProfessor);
        return courseMapper.toListViewDTO(courses);
    }
}
