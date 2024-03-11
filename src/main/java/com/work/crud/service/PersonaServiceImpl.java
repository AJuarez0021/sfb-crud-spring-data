/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.service;

import com.work.crud.dto.PersonaDTO;
import com.work.crud.dto.ResponseDTO;
import com.work.crud.model.Eleccion;
import com.work.crud.model.Persona;
import com.work.crud.repository.EleccionRepository;
import com.work.crud.repository.PersonaRepository;
import com.work.crud.transformer.PersonaTransformer;
import com.work.crud.util.TipoRegistro;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author linux
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    /**
     * The persona repository.
     */
    @Autowired
    private PersonaRepository personaRepository;

    /**
     * The eleccion repository.
     */
    @Autowired
    private EleccionRepository eleccionRepository;

    /**
     * The persona transform.
     */
    @Autowired
    private PersonaTransformer personaTransform;

    /**
     * Consultar.
     *
     * @return the response DTO
     */
    @Override
    public ResponseDTO<List<PersonaDTO>> consultar() {
        ResponseDTO<List<PersonaDTO>> respuesta = new ResponseDTO<>();
        List<Persona> lista = personaRepository.findAll();
        respuesta.setPayload(personaTransform.transform(lista));
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
    public ResponseDTO<PersonaDTO> consultar(Integer id) {
        ResponseDTO<PersonaDTO> respuesta = new ResponseDTO<>();
        Optional<Persona> persona = personaRepository.findById(id);
        if (!persona.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "La persona no existe");
        }
        respuesta.setPayload(personaTransform.transform(persona.get()));
        respuesta.setMessage(HttpStatus.OK.getReasonPhrase());
        return respuesta;
    }

    /**
     * Registrar.
     *
     * @param personaDto the persona dto
     * @return the response DTO
     */
    @Override
    public ResponseDTO<PersonaDTO> registrar(PersonaDTO personaDto) {
        return guardar(personaDto, TipoRegistro.GUARDAR);
    }

    /**
     * Actualizar.
     *
     * @param personaDto the persona dto
     * @return the response DTO
     */
    @Override
    public ResponseDTO<PersonaDTO> actualizar(PersonaDTO personaDto) {
        return guardar(personaDto, TipoRegistro.ACTUALIZAR);
    }

    /**
     * Eliminar.
     *
     * @param id the id
     */
    @Override
    public void eliminar(Integer id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (!persona.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "La persona no se pudo eliminar por que no existe con ese identificador");
        }
        personaRepository.delete(persona.get());
    }

    /**
     * Guardar.
     *
     * @param personaDto the persona dto
     * @param tipo the tipo
     * @return the response DTO
     */
    private ResponseDTO<PersonaDTO> guardar(PersonaDTO personaDto, TipoRegistro tipo) {
        ResponseDTO<PersonaDTO> respuesta = new ResponseDTO<>();
        Persona persona = new Persona();
        persona.setIdPersona(tipo == TipoRegistro.GUARDAR ? null : personaDto.getId());
        persona.setApellidos(personaDto.getApellidos());
        persona.setNombres(personaDto.getNombres());
        persona.setEdad(personaDto.getEdad());
        Optional<Eleccion> eleccion = eleccionRepository.findById(personaDto.getEleccion().getId());
        if (!eleccion.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo registrar la encuenta, no existe la eleccion");
        }
        persona.setIdEleccion(eleccion.get());
        respuesta.setPayload(personaTransform.transform(personaRepository.save(persona)));
        respuesta.setMessage(HttpStatus.CREATED.getReasonPhrase());
        return respuesta;
    }

}
