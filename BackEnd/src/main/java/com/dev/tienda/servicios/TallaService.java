package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Talla;
import com.dev.tienda.repositorios.ITallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallaService {
    @Autowired
    private ITallaRepository tallaRepository;



    public List<Talla> traerTodos(){
        return tallaRepository.findAll();
    }

}
