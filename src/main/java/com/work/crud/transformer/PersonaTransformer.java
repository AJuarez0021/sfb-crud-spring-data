/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.transformer;

import com.work.crud.dto.EleccionDTO;
import com.work.crud.dto.PersonaDTO;
import com.work.crud.model.Eleccion;
import com.work.crud.model.Persona;
import org.springframework.stereotype.Component;

/**
 *
 * @author linux
 */
@Component
public class PersonaTransformer implements Transformer<PersonaDTO, Persona> {

    @Override
    public PersonaDTO transform(Persona persona) {
        PersonaDTO personaDto = new PersonaDTO();
        personaDto.setId(persona.getIdPersona());
        personaDto.setNombres(persona.getNombres());
        personaDto.setApellidos(persona.getApellidos());
        personaDto.setEdad(persona.getEdad());
        Eleccion eleccion = persona.getIdEleccion();
        personaDto.setEleccion(new EleccionDTO(eleccion.getIdEleccion(),
                eleccion.getDescEleccion()));
        return personaDto;
    }

}
