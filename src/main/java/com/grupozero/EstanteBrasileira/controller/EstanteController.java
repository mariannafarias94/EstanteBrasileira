/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupozero.EstanteBrasileira.controller;

import com.grupozero.EstanteBrasileira.dto.AutorDTO;
import com.grupozero.EstanteBrasileira.dto.LivroDTO;
import com.grupozero.EstanteBrasileira.service.EstanteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author faria
 */
@RestController
@RequestMapping("/estante") // Base path para o controller
public class EstanteController {
    
    @Autowired
    private EstanteService estanteService;

    // Endpoint para obter todos os livros
    @GetMapping("/livros")
    public List<LivroDTO> obterLivros() {
        return estanteService.obterTodosOsLivros();
    }

    // Endpoint para obter livros de um autor específico, passando o ID do autor
    @GetMapping("/livros/autor/{autorId}")
    public List<LivroDTO> obterLivrosPorAutor(@PathVariable Long autorId) {
        return estanteService.obterLivrosPorAutor(autorId);
    }

    // Endpoint para obter todos os autores
    @GetMapping("/autores")
    public List<AutorDTO> obterAutores() {
        return estanteService.obterTodosAutores();
    }
    
    // Endpoint para obter informações de um livro específico pelo ID
    @GetMapping("/livros/{Id}")
    public LivroDTO obterLivroPorId(@PathVariable Long Id) {
    return estanteService.obterLivroPorId(Id);
}
}
