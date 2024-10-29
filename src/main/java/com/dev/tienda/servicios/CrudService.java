package com.dev.tienda.servicios;

import java.util.List;

public interface CrudService<T> {
    List<T> traerTodos();
    T traer(String nombre);
    void guardar(T entity);
    void actualizar(T entity);
    void eliminar(T entity);
    void eliminar(Long entityID);
}
