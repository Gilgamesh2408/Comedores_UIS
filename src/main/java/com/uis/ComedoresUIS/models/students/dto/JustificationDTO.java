package com.uis.ComedoresUIS.models.students.dto;

import com.uis.ComedoresUIS.models.students.enums.JustificationType;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JustificationDTO {

    private JustificationType type;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private String description;
    private Long student;

}
