package org.example.university.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.university.dto.base.AddBaseDTO;

@Setter
@Getter
public class AddUserDTO extends AddBaseDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String family;

    @Positive
    private long nationalCode;

    @NotBlank
    private String genderString;

    @NotNull
    private Long birthDayTimeStamp;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
