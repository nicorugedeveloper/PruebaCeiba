package com.ceiba.biblioteca.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioBookOutDTO {
    private Long id;
    private Date fechaMaximaDevolucion;

}
