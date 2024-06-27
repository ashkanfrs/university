package org.example.university.mapper;

import lombok.AllArgsConstructor;
import org.example.university.dto.student.AddStudentDTO;
import org.example.university.dto.student.UpdateStudentDTO;
import org.example.university.dto.student.ViewStudentDTO;
import org.example.university.entity.Course;
import org.example.university.entity.Student;
import org.example.university.enums.Gender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final ModelMapper mapper;

    public Student toEntity(AddStudentDTO addStudentDTO) {
        Student student = mapper.map(addStudentDTO, Student.class);

        student.setGender(addStudentDTO.getGenderString()
                .equals("MALE") ? Gender.MALE : Gender.FEMALE);
        student.setBirthDay(new Date(addStudentDTO.getBirthDayTimeStamp()));

        return student;
    }

    public Student toEntity(UpdateStudentDTO updateStudentDTO) {
        Student student = mapper.map(updateStudentDTO, Student.class);

        student.setGender(updateStudentDTO.getGenderString()
                .equals("MALE") ? Gender.MALE : Gender.FEMALE);
        student.setBirthDay(new Date(updateStudentDTO.getBirthDayTimeStamp()));

        return student;
    }

    public ViewStudentDTO toViewDto(Student student) {
        ViewStudentDTO viewStudentDTO = mapper.map(student, ViewStudentDTO.class);

        List<Integer> coursesCode = student.getCourses().stream()
                .map(Course::getCode)
                .toList();
        viewStudentDTO.setCourseCodes(coursesCode);

        viewStudentDTO.setBirthDayTimeStamp(student.getBirthDay().getTime());

        viewStudentDTO.setGenderString(student.getGender().name());

        return viewStudentDTO;
    }

    public List<ViewStudentDTO> toListViewDTO(List<Student> studentList) {
        return studentList.stream().map(this::toViewDto).toList();
    }
}
