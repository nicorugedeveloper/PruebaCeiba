package com.ceiba.biblioteca.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Data
public class DTOFecha {
    private Date fechaInicial;
    private Date fechaFinal;
    private Date fecha;
    private Integer diasHabiles;
}
