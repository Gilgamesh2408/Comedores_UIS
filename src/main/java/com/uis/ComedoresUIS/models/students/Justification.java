package com.uis.ComedoresUIS.models.students;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uis.ComedoresUIS.models.students.enums.JustificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Justification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JustificationType type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInit;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    private String description;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

}
