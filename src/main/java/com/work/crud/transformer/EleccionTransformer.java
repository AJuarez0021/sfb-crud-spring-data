/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.transformer;

import com.work.crud.dto.EleccionDTO;
import com.work.crud.model.Eleccion;
import org.springframework.stereotype.Component;

/**
 *
 * @author linux
 */
@Component
public class EleccionTransformer implements Transformer<EleccionDTO, Eleccion> {

    @Override
    public EleccionDTO transform(Eleccion eleccion) {
        return new EleccionDTO(eleccion.getIdEleccion(),
                eleccion.getDescEleccion());
    }

}
