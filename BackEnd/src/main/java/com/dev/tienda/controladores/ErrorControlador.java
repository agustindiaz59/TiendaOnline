package com.dev.tienda.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControlador implements ErrorController {


    @GetMapping("/404")
    public String error404(){
        return "404";
    }
}
