package com.ceiba.biblioteca.service.Impl;

import com.ceiba.biblioteca.DTO.DTOFecha;
import com.ceiba.biblioteca.DTO.UsuarioBookDTO;
import com.ceiba.biblioteca.DTO.UsuarioBookOutDTO;
import com.ceiba.biblioteca.DTO.UsuarioBookInDTO;
import com.ceiba.biblioteca.entities.UsuarioBook;
import com.ceiba.biblioteca.repository.BookRepository;
import com.ceiba.biblioteca.repository.UsuarioBookRepository;
import com.ceiba.biblioteca.service.IPretamosService;
import com.ceiba.biblioteca.service.IfechaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PrestamosService implements IPretamosService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsuarioBookRepository usuarioBookRepository;
    @Autowired
    private IfechaService ifechaService;


    @Override
    public UsuarioBookOutDTO guardarUsuarioBook(UsuarioBookInDTO usuarioBookInDTO) throws Exception {
        if (Objects.isNull(usuarioBookInDTO)) {
            System.out.println(" Error en parametro de entrada.");
            return null;
        }

        validaciones(usuarioBookInDTO);
        DTOFecha fechaVigencia = validacionFecha(usuarioBookInDTO);


        ModelMapper mapper = new ModelMapper();

        UsuarioBook usuarioBook = mapper.map(usuarioBookInDTO, UsuarioBook.class);
        usuarioBook.setFechaFinal(fechaVigencia.getFecha());
        usuarioBook.setTipoUsuario(usuarioBookInDTO.getTipoUsuario());

        usuarioBookRepository.save(usuarioBook);


        UsuarioBookOutDTO usuarioBookOutDTO = new UsuarioBookOutDTO();
        usuarioBookOutDTO.setId(usuarioBook.getId());
        usuarioBookOutDTO.setFechaMaximaDevolucion(usuarioBook.getFechaFinal());

        return usuarioBookOutDTO;

    }

    @Override
    public UsuarioBookDTO getUserBook(Long id) throws Exception {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Error en parametro de entrada.");
        }

        try {
            Optional<UsuarioBook> usuarioBook = usuarioBookRepository.findById(id);

            if (Objects.isNull(usuarioBook)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no existe o no a prestado ningun libro");
            }
            UsuarioBookDTO usuarioBookDTO = UsuarioBookDTO.builder()
                    .id(id)
                    .isbn(usuarioBook.get().getIsbn())
                    .tipoUsuario(usuarioBook.get().getTipoUsuario())
                    .fechaMaximaDevolucion(usuarioBook.get().getFechaFinal())
                    .identificacionUsuario(usuarioBook.get().getIndetificacionUsuario())
                    .build();

            return usuarioBookDTO;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception(ex.getMessage());
        }

    }

    private DTOFecha validacionFecha(UsuarioBookInDTO usuarioBookInDTO) throws Exception {

        Integer dias = 0;
        if (usuarioBookInDTO.getTipoUsuario().equals(1)) {
            dias = 10;
        } else if (usuarioBookInDTO.getTipoUsuario().equals(2)) {
            dias = 8;
        } else if (usuarioBookInDTO.getTipoUsuario().equals(3)) {
            dias = 7;
        }

        DTOFecha fecha = ifechaService.sumarDiasHabilesAFecha(usuarioBookInDTO.getFechaInicial(), dias);

        return fecha;

    }

    private void validaciones(UsuarioBookInDTO usuarioBookInDTO) {

        List<UsuarioBook> consulta = usuarioBookRepository.findUsuarioBookByIdentificacionUsuario(usuarioBookInDTO.getIndetificacionUsuario());

        if (consulta.get(0).getTipoUsuario().equals(3) && consulta.size() > 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario con identificación " + usuarioBookInDTO.getIndetificacionUsuario() +
                    " ya tiene libro prestado por lo cual no se le puede realizar otro préstamos. ");
        }

        if (usuarioBookInDTO.getTipoUsuario() > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuario no permitido en la biblioteca.");
        }

    }

}
