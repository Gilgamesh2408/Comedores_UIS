package com.uis.ComedoresUIS.entities.admins;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
public class DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInit;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "id_period")
    private PeriodEntity period;

}
