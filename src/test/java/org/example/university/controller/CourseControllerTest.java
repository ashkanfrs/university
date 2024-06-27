//package org.example.spring_project.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.spring_project.dto.course.AddCourseDTO;
//import org.example.spring_project.entity.Course;
//import org.example.spring_project.mapper.CourseMapper;
//import org.example.spring_project.mapper.ProfessorMapper;
//import org.example.spring_project.mapper.StudentMapper;
//import org.example.spring_project.service.CourseService;
//import org.example.spring_project.service.StudentService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CourseController.class)
//class CourseControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CourseService courseService;
//
//    @MockBean
//    private StudentMapper studentMapper;
//
//    @MockBean
//    private ProfessorMapper professorMapper;
//
//    @MockBean
//    private StudentService studentService;
//
//
//    @MockBean
//    private CourseMapper courseMapper;
//
//    @Test
//    void save() throws Exception {
//        AddCourseDTO addCourseDTO = new AddCourseDTO();
//        addCourseDTO.setCode(1);
//        addCourseDTO.setTitle("course1");
//        addCourseDTO.setUnits(3);
//
//        Course course = courseMapper.toEntity(addCourseDTO);
//
//        when(courseService.save(course)).thenReturn(course);
//
//        mockMvc.perform(post("/course/v1/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(addCourseDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.code").value(addCourseDTO.getCode()))
//                .andExpect(jsonPath("$.title").value(addCourseDTO.getTitle()))
//                .andExpect(jsonPath("$.units").value(addCourseDTO.getUnits()));
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void findAll() {
//    }
//}