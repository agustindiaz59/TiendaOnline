package com.dev.tienda.servicios;

import com.dev.tienda.modelos.*;
import com.dev.tienda.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements CrudService<Producto>{

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ImagenesService imagenesService;
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ITallaRepository tallaRepository;
    @Autowired
    private IImagenRepository imagenRepository;
    @Autowired
    private IColorRepository colorRepository;

    @Override
    public List<Producto> traerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto traer(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public void guardar(Producto entity) {
        //Verifica si ya existe un producto por su nombre
        Producto bd = productoRepository.findByNombre(entity.getNombre());
        Optional<Producto> aGuardar = Optional.ofNullable(bd);

        //Si ya existe en la base de datos lo modifica y lo vuelve a guardar
        if(aGuardar.isPresent()){
            aGuardar.get().setNombre(entity.getNombre());
            aGuardar.get().setDescripcion(entity.getDescripcion());
            aGuardar.get().setPrecio(entity.getPrecio());
            //Aprovechar el set para agregar tallas
            entity.getTallas().forEach(t -> {
                //Optional<Talla> op = Optional.ofNullable(tallaRepository.findByNumero(t.getNumero()));
                aGuardar.get().getTallas().add(t);
            });
            entity.getColores().forEach(c -> {
                //Optional<Color> op = Optional.ofNullable(colorRepository.findByNombre(c.getNombre()));
                aGuardar.get().getColores().add(c);
            });
            entity.getCategorias().forEach(cat -> {
                //Optional<Categoria> op = Optional.ofNullable(categoriaRepository.findByNombre(cat.getNombre()));
                aGuardar.get().getCategorias().add(cat);
            });
            entity.getImagenes().forEach(img -> {
                //Optional<Imagen> op = Optional.ofNullable(imagenRepository.findBySrc(img.getSrc()));
                aGuardar.get().getImagenes().add(img); //Hacer la misma verificacion para evitar duplicados en la bd
            });
        }

        //Si ya existe lo actualiza, si no lo guarda como uno nuevo (upsert)
        productoRepository.save(aGuardar.orElse(entity));

    }

    @Transactional
    @Override
    public void actualizar(Producto entity) {
        //Misma logica que guardar, por el momento
        guardar(entity);
    }

    @Override
    public void eliminar(Producto entity) {
        productoRepository.delete(entity);
    }

    @Override
    public void eliminar(Long entityID) {
        productoRepository.deleteById(entityID);
    }
}
