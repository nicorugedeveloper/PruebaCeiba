package com.ceiba.biblioteca.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table (name = "usuariobook")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "isbn")
    private String isbn;

    @NotNull
    @Column(name = "identificacion_usuario")
    private String indetificacionUsuario;

    @Column(name = "tipo_usuario")
    private Long tipoUsuario;

    @Column(name = "fecha_incial")
    private Date fechaInicial;

    @Column(name = "fecha_final")
    private Date fechaFinal;

}
