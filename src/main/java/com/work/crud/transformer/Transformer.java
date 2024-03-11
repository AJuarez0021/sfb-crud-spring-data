/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.work.crud.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author linux
 * @param <E>
 * @param <D>
 */
public interface Transformer<E, D> {

    /**
     * Transform.
     *
     * @param dto the dto
     * @return the e
     */
    public E transform(D dto);

    /**
     * Transform.
     *
     * @param dtos the dtos
     * @return the list
     */
    public default List<E> transform(List<D> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::transform).collect(Collectors.toList());
    }
}
