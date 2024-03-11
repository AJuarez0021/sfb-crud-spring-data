/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.service;

import com.work.crud.dto.EleccionDTO;
import com.work.crud.dto.ResponseDTO;
import com.work.crud.model.Eleccion;
import com.work.crud.repository.EleccionRepository;
import com.work.crud.transformer.EleccionTransformer;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author linux
 */
@Service
@Slf4j
public class EleccionServiceImpl implements EleccionService {

    /**
     * The eleccion repository.
     */
    @Autowired
    private EleccionRepository eleccionRepository;

    /**
     * The eleccion transform.
     */
    @Autowired
    private EleccionTransformer eleccionTransform;

    /**
     * Consultar.
     *
     * @return the response DTO
     */
    @Override
    public ResponseDTO<List<EleccionDTO>> consultar() {
        ResponseDTO<List<EleccionDTO>> respuesta = new ResponseDTO<>();
        List<Eleccion> lista = eleccionRepository.findAll();
        log.debug("{}",lista);
        respuesta.setPayload(eleccionTransform.transform(lista));
        respuesta.setMessage(HttpStatus.OK.getReasonPhrase());
        return respuesta;
    }

    /**
     * Consultar.
     *
     * @param id the id
     * @return the response DTO
     */
    @Override
    public ResponseDTO<EleccionDTO> consultar(Integer id) {
        ResponseDTO<EleccionDTO> respuesta = new ResponseDTO<>();
        Optional<Eleccion> eleccion = eleccionRepository.findById(id);
        if (!eleccion.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "La eleccion no existe");
        }
        respuesta.setPayload(eleccionTransform.transform(eleccion.get()));
        respuesta.setMessage(HttpStatus.OK.getReasonPhrase());
        return respuesta;
    }

}
