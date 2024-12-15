package com.dev.tienda.dto;

import jakarta.validation.constraints.*;

import java.util.LinkedHashSet;

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

        LinkedHashSet<ImagenDTO> imagenes,

        @NotEmpty
        LinkedHashSet<ColorDTO> colores,
        LinkedHashSet<CategoriaDTO> categorias
){
}
