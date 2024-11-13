package com.uis.ComedoresUIS.models.students;

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

    private Boolean type;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private String description;
    private Long student;

}
