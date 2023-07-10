package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.DTO.DTOFecha;

import java.util.Date;

public interface IfechaService {

    public DTOFecha sumarDiasHabilesAFecha(Date fecha, Integer diasHabiles) throws Exception;
}
