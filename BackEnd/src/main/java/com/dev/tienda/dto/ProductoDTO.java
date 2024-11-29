package com.dev.tienda.dto;

import com.dev.tienda.modelos.Color;

import java.util.LinkedHashSet;

public record ProductoDTO (
        String nombre,
        String descripcion,
        Float precio,
        LinkedHashSet<String> srcFotos,
        LinkedHashSet<Color> colores,
        LinkedHashSet<String> nombreCategorias
){
}
