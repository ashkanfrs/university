package org.example.university.dto.course;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.base.ViewBaseDTO;

import java.util.List;

@Setter
@Getter
public class ViewCourseDTO extends ViewBaseDTO {
    private int code;
    private String title;
    private int units;
    private String nameProfessor;
    private List<Long> studentNumbers;
}
