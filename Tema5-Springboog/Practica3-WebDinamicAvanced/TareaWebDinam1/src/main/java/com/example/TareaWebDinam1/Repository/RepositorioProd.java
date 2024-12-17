package com.example.TareaWebDinam1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProd extends JpaRepository<Producto, String> {

}
