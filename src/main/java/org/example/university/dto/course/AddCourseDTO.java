package org.example.university.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.university.dto.base.AddBaseDTO;

@Setter
@Getter
public class AddCourseDTO extends AddBaseDTO {
    @Positive
    private int code;

    @NotBlank
    private String title;

    @Positive
    private int units;
}
