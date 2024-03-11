/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.controller;

import com.work.crud.dto.PersonaDTO;
import com.work.crud.dto.ResponseDTO;
import com.work.crud.service.PersonaService;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author linux
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/encuesta")
@Slf4j
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(path = "/personas", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<List<PersonaDTO>>> consultar() {
        ResponseDTO<List<PersonaDTO>> respuesta = personaService.consultar();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/persona/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<PersonaDTO>> consultarPorId(@PathVariable Integer id) {
        log.debug("Consultar: {}", id);
        ResponseDTO<PersonaDTO> respuesta = personaService.consultar(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/persona/hateoas/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<EntityModel> consultarPorIdHateoas(@PathVariable Integer id) {
        log.debug("Consultar: {}", id);
        ResponseDTO<PersonaDTO> respuesta = personaService.consultar(id);
        Link link = linkTo(methodOn(this.getClass()).consultarPorId(id)).withSelfRel();
        EntityModel<ResponseDTO<PersonaDTO>> recurso = EntityModel.of(respuesta);
        recurso.add(link.withRel("recurso"));
        return new ResponseEntity<>(recurso, HttpStatus.OK);
    }

    @PostMapping(path = "/persona", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<PersonaDTO>> registrar(@RequestBody PersonaDTO persona) {
        log.debug("Persona: {}", persona);
        ResponseDTO<PersonaDTO> respuesta = personaService.registrar(persona);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(respuesta.getPayload().getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/persona", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<PersonaDTO>> actualizar(@RequestBody PersonaDTO persona) {
        log.debug("Persona: {}", persona);
        ResponseDTO<PersonaDTO> respuesta = personaService.actualizar(persona);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping(path = "/persona/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.debug("Eliminar: {}", id);
        personaService.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
