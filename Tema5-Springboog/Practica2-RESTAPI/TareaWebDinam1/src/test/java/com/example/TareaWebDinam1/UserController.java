package com.example.TareaWebDinam1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/pruebas")
    public String pruebas(Model model) {
        // LE PASAMOS UN MODELO ( DATOS ) AL TEMPLATE
        model.addAttribute("title", "El Título");
        model.addAttribute("nombre", "Pepe palomo");
        model.addAttribute("apellido", "Gomez Osorio");

        // ESTE TEXTO QUE SE RETORNA ES EL NOMBRE DEL FICHERO/PLANTILLA WEB QUE SE ABRIRÁ EN EL NAVEGADOR        
        return "pruebas";
    }
} 
