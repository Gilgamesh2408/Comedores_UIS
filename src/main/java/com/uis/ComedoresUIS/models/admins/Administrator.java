package com.uis.ComedoresUIS.models.admins;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(unique = true)
    private Integer codeAdmin;
    @Column(length = 350)
    private String password;

    private Boolean superUser;

}
