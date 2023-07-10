package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.entities.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepostory extends JpaRepository<TipoUsuarioEntity,Long> {
}
