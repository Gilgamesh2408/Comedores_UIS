package com.uis.ComedoresUIS.persistence.models.students;

import com.uis.ComedoresUIS.persistence.models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(unique = true)
    private String codeStudent;
    @Column(length = 350)
    private String password;
    @Column(unique = true)
    private String personalEmail;
    @Column(unique = true)
    private String institutionalEmail;

    private Boolean activate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @ManyToOne
    @JoinColumn(name = "id_services")
    private AccessToService access;


}
