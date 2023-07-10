package com.ceiba.biblioteca.DTO;

import com.ceiba.biblioteca.entities.TipoUsuarioEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
public class UsuarioBookInDTO {

    private String isbn;
    private String indetificacionUsuario;
    private Long tipoUsuario;
    private Date fechaInicial;

}
