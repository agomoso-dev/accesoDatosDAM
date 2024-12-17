package com.example.TareaWebDinam1.Controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // Anotación - es un controlador REST

@RequestMapping("/api") // Anotacion - prefijo del api
public class ProductController {

    // Este metodo indica todas las peiticiones GET a "/api/users"
    @GetMapping("users")
    public Map<String, Object> pruebas() {
        // Aquí se crea un objeto Map que se utilizará para devolver datos en formato JSON
        Map<String, Object> body = new HashMap<>();
        
        // Completa el contenido del Map para devolver los datos en JSON
        body.put("title", "titulo hola mundo");   
        body.put("nombre", "nombre");               
        body.put("apellidos", "apellidos");          

        return body; // JSON 
        
    }

    // Majena las interacciones POST a "/api/create"
    @PostMapping("create")
    public String postMethodName(@RequestBody String entity) {
        // Este método recibe un String y lo procesa
        
        try {
            // Dentro del bloque try, se intenta crear un archivo temporal
            File.createTempFile("asda", "adsasd");
        } catch (Exception ex) {
        }        
        // El método retorna una cadena de texto en la que se reemplaza "Gomez" por "Manuelto".
        return entity.replace("Gomez", "Manuelto");
    }
    // Método PUT que actualiza datos
    @PutMapping("/update")
    public String updateUser(@RequestBody String entity) {
        // Aquí solo simulamos la actualización y devolvemos los datos recibidos
        return "Datos actualizados: " + entity;
    }

}