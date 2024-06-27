package org.example.university.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.base.ViewBaseDTO;

@Setter
@Getter
public class ViewUserDTO extends ViewBaseDTO {
    private String name;
    private String family;
    private long nationalCode;
    private String genderString;
    private Long birthDayTimeStamp;
    private String username;
}
