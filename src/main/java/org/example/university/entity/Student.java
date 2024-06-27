package org.example.university.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.university.enums.AcademicLevel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends User {

    @Column(unique = true , nullable = false , updatable = false)
    private long stdNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicLevel academicLevel;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
}
