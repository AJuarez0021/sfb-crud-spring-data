/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author linux
 */
@Data
@NoArgsConstructor
public class PersonaDTO {

    private Integer id;
    private Integer edad;
    private String nombres;
    private String apellidos;
    private EleccionDTO eleccion;

}
