package org.example.university.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.university.enums.Gender;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String family;

    @Column(unique = true, updatable = false)
    private long nationalCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date birthDay;

    @Column(unique = true , nullable = false , updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
