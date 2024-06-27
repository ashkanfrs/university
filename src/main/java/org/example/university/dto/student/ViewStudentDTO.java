package org.example.university.dto.student;

import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.user.ViewUserDTO;

import java.util.List;

@Setter
@Getter
public class ViewStudentDTO extends ViewUserDTO {
    private long stdNumber;
    private String academicLevel;
    private List<Integer> courseCodes;
}
