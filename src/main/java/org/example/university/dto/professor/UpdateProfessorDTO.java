package org.example.university.dto.professor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.user.UpdateUserDTO;

@Setter
@Getter
public class UpdateProfessorDTO extends UpdateUserDTO {
    @NotBlank
    private String academicRank;
}
