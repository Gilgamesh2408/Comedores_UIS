package com.uis.ComedoresUIS.models.students;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Justification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInit;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    @Lob
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

}
