package com.example.TareaWebDinam1.Controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.TareaWebDinam1.Producto;
import com.example.TareaWebDinam1.RepositorioProd;



@Controller
public class UserController {
    @Autowired
    private RepositorioProd repo;
    @GetMapping("/pruebas")
    public String pruebas(Model model) {
        // LE PASAMOS UN MODELO ( DATOS ) AL TEMPLATE
        model.addAttribute("title", "El Título");
        model.addAttribute("nombre", "Pepe palomo");
        model.addAttribute("apellido", "Gomez Osorio");

        // ESTE TEXTO QUE SE RETORNA ES EL NOMBRE DEL FICHERO/PLANTILLA WEB QUE SE ABRIRÁ EN EL NAVEGADOR        
        return "pruebas";
    }
    @GetMapping("/productoUnico") // Prueba unitaria al empezar
    public String producto_unitario(Model model) {
        // Pruebas sobre productos
        Producto productoUnico=new Producto("333", "Zapatos", "De cuero", 5.0, 10);

        // Añadir producto
        model.addAttribute("MiProducto", productoUnico);
        // Template Asociado
        return "productos/html_ProductoUnico";
    }
    @GetMapping("/productosVarios")// Esta prueba es para probar a meter varios
    public String productos_pruebas(Model model) {
        // Pruebas sobre productos
           List<Producto> productos = Arrays.asList(
            new Producto("323", "Cojines", "De algodon", 3.5, 20),
            new Producto("222", "Pantalones", "De lana", 4.0, 15),
            new Producto("333", "Zapatos", "De cuero", 5.0, 10)
            );

        // Añadir producto
        model.addAttribute("TodosMisProductos", productos);
        // Template Asociado
        return "productos/html_Productos";
    }

    @GetMapping("/productosRepo")  // Ruta para mostrar todos los productos
    public String mostrarProductos(Model model) {
        // Obtener todos los productos desde el repositorio
        List<Producto> productos = repo.findAll(); // He creado un nuevo repositorio RepositorioProd
        
        // Pasar la lista de productos al modelo
        model.addAttribute("productos", productos);
        
        // Devolver el nombre de la vista (por ejemplo, "productos/ver_productos")
        return "productos/pruebas_RepoProductos";
    }
} 
