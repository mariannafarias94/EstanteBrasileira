/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupozero.EstanteBrasileira.repository;

import com.grupozero.EstanteBrasileira.model.Autor;
import com.grupozero.EstanteBrasileira.model.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faria
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Busca livros pelo título, ignorando maiúsculas/minúsculas
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    
    boolean existsByTituloAndAutor(String titulo, Autor autor);

    // Busca livros pelo nome do autor, ignorando maiúsculas/minúsculas
    @Query("SELECT l FROM Livro l JOIN l.autor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Livro> findByAutorNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);

    // Busca livros pela editora, ignorando maiúsculas/minúsculas
    List<Livro> findByEditoraContainingIgnoreCase(String editora);

    public List<Livro> findByAutorIn(List<Autor> autores);
    
}
