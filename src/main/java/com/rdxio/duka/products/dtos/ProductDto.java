/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.rdxio.duka.products.dtos;

import java.util.UUID;

/**
 *
 * @author 7X
 */
public record ProductDto(
    UUID id,
    String name,
    String description,
    Double price,
    String imageUrl,
    String category,
    String vatCategory
) {

}
