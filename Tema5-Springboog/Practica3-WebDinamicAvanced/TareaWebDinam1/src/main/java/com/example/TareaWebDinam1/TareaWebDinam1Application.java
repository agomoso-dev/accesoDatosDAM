package com.example.TareaWebDinam1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.TareaWebDinam1.Repository.RepositorioProd;
import com.example.TareaWebDinam1.object.Producto;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class TareaWebDinam1Application implements  CommandLineRunner {
    @Autowired
    private RepositorioProd repoProd;
	public static void main(String[] args) {
		SpringApplication.run(TareaWebDinam1Application.class, args);
	}

       @Override
    public void run(String... args) throws Exception {
        agregarProductos();
    }
    @Transactional
    public void agregarProductos(){
        
        Producto p1 =new Producto("333", "Zapatos", "De cuero", 5.0, 10);
        repoProd.save(p1);
        System.out.println("Producto agregado: " + p1);
    }


}
