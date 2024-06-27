package org.example.university.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.base.UpdateBaseDTO;

@Setter
@Getter
public class UpdateUserDTO extends UpdateBaseDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String family;

    @NotBlank
    private String genderString;

    @NotNull
    private Long birthDayTimeStamp;

    @NotBlank
    private String password;
}
