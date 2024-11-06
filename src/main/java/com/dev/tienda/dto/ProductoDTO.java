package com.dev.tienda.dto;

import java.util.Set;

public record ProductoDTO (
    String nombre,
    Float precio,
    Set<ImagenDTO> fotos
){
}
