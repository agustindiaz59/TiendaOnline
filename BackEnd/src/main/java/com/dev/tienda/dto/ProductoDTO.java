package com.dev.tienda.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

/**
 * Clase DTO para la transferencia de datos de un producto al Front
 *
 *
 * @param nombre Nombre del producto
 * @param descripcion Descripcion del producto
 * @param precio Precio unitario
 * @param imagenes Imagenes asociadas
 * @param colores Colores disponibles
 * @param categorias Categorias aplicables al producto
 */
public record ProductoDTO (

        @NotEmpty
        @NotBlank
        @Size(min = 0, max = 255)
        String nombre,

        @NotBlank
        @NotEmpty
        @Size(min = 0, max = 255)
        String descripcion,

        @Positive
        Float precio,

        Set<ImagenDTO> imagenes,

        @NotEmpty
        Set<ColorDTO> colores,
        Set<CategoriaDTO> categorias
){
}
