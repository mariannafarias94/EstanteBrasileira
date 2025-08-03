/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupozero.EstanteBrasileira.repository;

import com.grupozero.EstanteBrasileira.model.Autor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author faria
 */
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Busca autores pelo nome, ignorando maiúsculas/minúsculas
    Optional<Autor> findByNomeIgnoreCase(String nome); 
    
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    // Busca autores que possuem livros cadastrados
    @Query("SELECT a FROM Autor a WHERE a.livros IS NOT EMPTY")
    List<Autor> findAutoresWithLivros();
    
    // Busca autores sem livros cadastrados
    @Query("SELECT a FROM Autor a WHERE a.livros IS EMPTY")
    List<Autor> findAutoresWithoutLivros();

    // Busca autor pelo nome exato (caso desejado)
    List<Autor> findByNome(String nome);
    
}
