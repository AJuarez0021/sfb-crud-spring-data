/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.service;

import com.work.crud.dto.EleccionDTO;
import com.work.crud.dto.ResponseDTO;
import java.util.List;

/**
 *
 * @author linux
 */
public interface EleccionService {

    /**
     * Consultar.
     *
     * @return the response DTO
     */
    ResponseDTO<List<EleccionDTO>> consultar();

    /**
     * Consultar.
     *
     * @param id the id
     * @return the response DTO
     */
    ResponseDTO<EleccionDTO> consultar(Integer id);
}
