package com.uis.ComedoresUIS.models.admins;

import com.uis.ComedoresUIS.models.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(unique = true)
    private String codeAdmin;
    @Column(length = 350)
    private String password;

    private Boolean superUser;

    @Enumerated(EnumType.STRING)
    private Role role;

}
