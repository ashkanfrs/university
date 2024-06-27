package org.example.university.dto.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.user.UpdateUserDTO;

@Setter
@Getter
public class UpdateStudentDTO extends UpdateUserDTO {
    @NotBlank
    private String academicLevel;
}
