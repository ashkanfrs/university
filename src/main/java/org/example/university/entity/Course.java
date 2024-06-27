package org.example.university.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Course extends BaseEntity {

    @Column(unique = true, nullable = false)
    private int code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int units;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(cascade = {CascadeType.ALL} , fetch = FetchType.EAGER)
    @JoinTable(
            name = "Course_Student",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students = new HashSet<>();
}