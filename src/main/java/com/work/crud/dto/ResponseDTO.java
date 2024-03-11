/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.crud.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author linux
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseDTO<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The content.
     */
    private transient T payload;

    /**
     * The message.
     */
    private String message;
}
