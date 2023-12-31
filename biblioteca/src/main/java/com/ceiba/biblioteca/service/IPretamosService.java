package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.DTO.UsuarioBookDTO;
import com.ceiba.biblioteca.DTO.UsuarioBookOutDTO;
import com.ceiba.biblioteca.DTO.UsuarioBookInDTO;

public interface IPretamosService {

    /**
     * Guardamos Información Usuario y Libro.
     * @param usuarioBookInDTO
     * @return
     */
    UsuarioBookOutDTO guardarUsuarioBook(UsuarioBookInDTO usuarioBookInDTO) throws Exception;

    /**
     * Retorna información del usario.
     * @param id
     * @return
     * @throws Exception
     */
    UsuarioBookDTO getUserBook(Long id) throws Exception;

}
