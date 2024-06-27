package org.example.university.service;

import org.example.university.entity.Course;
import org.example.university.exception.ConflictException;
import org.example.university.exception.NotFoundException;
import org.example.university.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void save() {
        // Given
        Course course1 = new Course();
        course1.setCode(1);
        course1.setTitle("course1");
        course1.setUnits(3);

        Course course2 = new Course();
        course2.setCode(1);
        course2.setTitle("course2");
        course2.setUnits(2);

        when(courseRepository.findByCode(1)).thenReturn(Optional.of(course1));

        // Then
        Assertions.assertThrows(ConflictException.class,() ->  courseService.save(course2));

        // Given
        course2.setCode(2);
        when(courseRepository.save(course2)).thenReturn(course2);

        // When
        Course course = courseService.save(course2);

        // Then
        Assertions.assertEquals(course.getCode(), course2.getCode());
        Assertions.assertEquals(course.getTitle(), course2.getTitle());
        Assertions.assertEquals(course.getUnits(), course2.getUnits());
    }

    @Test
    void findByCode() {
        // Given
        Course course = new Course();
        course.setCode(1);
        course.setTitle("course1");
        course.setUnits(3);

        when(courseRepository.findByCode(1)).thenReturn(Optional.of(course));

        // When
        Course course1 = courseService.findByCode(1);

        // Then
        Assertions.assertEquals(course1.getCode(), course.getCode());
        Assertions.assertEquals(course1.getTitle(), course.getTitle());
        Assertions.assertEquals(course1.getUnits(), course.getUnits());

        // Given
        when(courseRepository.findByCode(2)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(NotFoundException.class,() ->  courseService.findByCode(2));
    }
}