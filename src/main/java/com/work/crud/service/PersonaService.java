/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.work.crud.service;

import com.work.crud.dto.PersonaDTO;
import com.work.crud.dto.ResponseDTO;
import java.util.List;

/**
 *
 * @author linux
 */
public interface PersonaService {

    /**
     * Consultar.
     *
     * @return the response DTO
     */
    ResponseDTO<List<PersonaDTO>> consultar();

    /**
     * Consultar.
     *
     * @param id the id
     * @return the response DTO
     */
    ResponseDTO<PersonaDTO> consultar(Integer id);

    /**
     * Registrar.
     *
     * @param personaDto the persona dto
     * @return the response DTO
     */
    ResponseDTO<PersonaDTO> registrar(PersonaDTO personaDto);

    /**
     * Actualizar.
     *
     * @param personaDto the persona dto
     * @return the response DTO
     */
    ResponseDTO<PersonaDTO> actualizar(PersonaDTO personaDto);

    /**
     * Eliminar.
     *
     * @param id the id
     */
    void eliminar(Integer id);
}
