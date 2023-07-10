package com.ceiba.biblioteca.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tipousuario")
@Data
public class TipoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "detalle")
    private String detalle;

}
