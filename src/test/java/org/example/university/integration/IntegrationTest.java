package org.example.university.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.university.dto.course.AddCourseDTO;
import org.example.university.dto.course.UpdateCourseDTO;
import org.example.university.entity.Course;
import org.example.university.entity.Student;
import org.example.university.enums.AcademicLevel;
import org.example.university.enums.Gender;
import org.example.university.service.CourseService;
import org.example.university.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:application-test.properties"
})
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp(){
        courseService.deleteAll();
        studentService.deleteAll();
    }

    @Test
    void givenValidData_whenSaveCourse_thenIsCreatedShouldBeReturn() throws Exception {
        // Given
        AddCourseDTO addCourseDTO = new AddCourseDTO();
        addCourseDTO.setCode(4);
        addCourseDTO.setTitle("course4");
        addCourseDTO.setUnits(3);

        // When
        mockMvc.perform(post("/course/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCourseDTO)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(addCourseDTO.getCode()))
                .andExpect(jsonPath("$.title").value(addCourseDTO.getTitle()))
                .andExpect(jsonPath("$.units").value(addCourseDTO.getUnits()));
    }

    @Test
    void givenValidData_whenFindAllCourse_thenAllCourseShouldBeReturn() throws Exception {
        // Given
        Course course1 = new Course();
        course1.setCode(4);
        course1.setTitle("course4");
        course1.setUnits(3);

        Course course2 = new Course();
        course2.setCode(5);
        course2.setTitle("course5");
        course2.setUnits(3);

        courseService.save(course1);
        courseService.save(course2);

        // When
        mockMvc.perform(get("/course/v1/list"))
                // Then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void givenValidData_whenDeleteCourse_thenNoContentShouldBeReturn() throws Exception {
        // Given
        Course course1 = new Course();
        course1.setCode(4);
        course1.setTitle("course4");
        course1.setUnits(3);
        course1 = courseService.save(course1);

        // When
        mockMvc.perform(delete("/course/v1/delete/{id}", course1.getId()))
                // Then
                .andExpect(status().isNoContent());

        // When
        mockMvc.perform(get("/course/v1/find/{id}", course1.getId()))
                // Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Course Not found."));
    }

    @Test
    void givenValidData_whenUpdateCourse_thenOkShouldBeReturn() throws Exception {
        // Given
        Course course1 = new Course();
        course1.setCode(4);
        course1.setTitle("course4");
        course1.setUnits(3);
        course1 = courseService.save(course1);

        UpdateCourseDTO updateCourseDTO = new UpdateCourseDTO();
        updateCourseDTO.setId(course1.getId());
        updateCourseDTO.setTitle("course44");
        updateCourseDTO.setUnits(2);

        // When
        mockMvc.perform(put("/course/v1/update", course1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateCourseDTO)))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updateCourseDTO.getTitle()))
                .andExpect(jsonPath("$.units").value(updateCourseDTO.getUnits()));
    }

    @Test
    void givenValidData_whenFindByIdCourse_thenCourseShouldBeReturn() throws Exception {
        // Given
        Course course = new Course();
        course.setCode(4);
        course.setTitle("course4");
        course.setUnits(3);
        course = courseService.save(course);

        // When
        mockMvc.perform(get("/course/v1/find/{id}", course.getId()))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()))
                .andExpect(jsonPath("$.code").value(course.getCode()))
                .andExpect(jsonPath("$.title").value(course.getTitle()))
                .andExpect(jsonPath("$.units").value(course.getUnits()));
    }

    @Test
    void givenValidData_whenListStudents_thenAllStudentShouldBeReturn() throws Exception {
        // Given
        Course course = new Course();
        course.setCode(4);
        course.setTitle("course4");
        course.setUnits(3);
        course = courseService.save(course);

        Student student = new Student();
        student.setName("name1");
        student.setFamily("family1");
        student.setBirthDay(new Date());
        student.setGender(Gender.FEMALE);
        student.setStdNumber(1234567890L);
        student.setAcademicLevel(AcademicLevel.PHD);
        student.setNationalCode(9876543210L);
        student.setUsername("username1");
        student.setPassword("password1");
        studentService.save(student);

        course.getStudents().add(student);
        courseService.update(course);

        // When
        mockMvc.perform(get("/course/v1/{codeCourse}/students", course.getCode()))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
