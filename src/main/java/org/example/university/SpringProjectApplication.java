package org.example.university;

import lombok.AllArgsConstructor;
import org.example.university.entity.Course;
import org.example.university.entity.Student;
import org.example.university.enums.AcademicLevel;
import org.example.university.enums.Gender;
import org.example.university.service.CourseService;
import org.example.university.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class SpringProjectApplication implements CommandLineRunner {

    private final CourseService courseService;
    private final StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args){
        if(courseService.findAll().isEmpty()){
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
        }

    }
}
