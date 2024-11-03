package com.uis.ComedoresUIS.models.students;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(unique = true)
    private Integer codeStudent;
    @Column(length = 350)
    private String password;
    @Column(unique = true)
    private String personalEmail;
    @Column(unique = true)
    private String institutionalEmail;

    private Boolean activate;

    @ManyToOne
    @JoinColumn(name = "id_services")
    private AccessToService access;


}
