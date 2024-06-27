package org.example.university.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.user.AddUserDTO;

@Setter
@Getter
public class AddStudentDTO extends AddUserDTO {
    @Positive
    private long stdNumber;

    @NotBlank
    private String academicLevel;
}
