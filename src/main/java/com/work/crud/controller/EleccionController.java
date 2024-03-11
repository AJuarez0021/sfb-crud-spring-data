/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.controller;

import com.work.crud.dto.EleccionDTO;
import com.work.crud.dto.ResponseDTO;
import com.work.crud.service.EleccionService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linux
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/encuesta")
@Slf4j
public class EleccionController {

    @Autowired
    private EleccionService eleccionService;

    @GetMapping(path = "/elecciones", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<List<EleccionDTO>>> consultar() {
        log.debug("Listar");
        ResponseDTO<List<EleccionDTO>> respuesta = eleccionService.consultar();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/eleccion/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO<EleccionDTO>> consultarPorId(@PathVariable Integer id) {
        log.debug("Id: {}", id);
        ResponseDTO<EleccionDTO> respuesta = eleccionService.consultar(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/eleccion/hateoas/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<EntityModel> consultarPorIdHateoas(@PathVariable Integer id) {
        log.debug("Id: {}", id);
        Link link = linkTo(methodOn(this.getClass()).consultarPorId(id)).withSelfRel();
        ResponseDTO<EleccionDTO> respuesta = eleccionService.consultar(id);
        EntityModel<ResponseDTO<EleccionDTO>> recurso = EntityModel.of(respuesta);
        recurso.add(link.withRel("recurso"));
        return new ResponseEntity<>(recurso, HttpStatus.OK);
    }
}
