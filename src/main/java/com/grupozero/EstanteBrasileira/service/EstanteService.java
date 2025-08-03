/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupozero.EstanteBrasileira.service;

import com.grupozero.EstanteBrasileira.dto.LivroDTO;
import com.grupozero.EstanteBrasileira.dto.AutorDTO;
import com.grupozero.EstanteBrasileira.model.Livro;
import com.grupozero.EstanteBrasileira.model.Autor;
import com.grupozero.EstanteBrasileira.model.EstanteCategoria;
import com.grupozero.EstanteBrasileira.repository.LivroRepository;
import com.grupozero.EstanteBrasileira.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstanteService {

    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;

    // Obtém todos os livros
    public List<LivroDTO> obterTodosOsLivros() {
        return converteDadosLivro(livroRepository.findAll());
    }

    // Obtém livros por categoria (autor)
    public List<LivroDTO> obterLivrosPorCategoria(String nomeCategoria) {
        EstanteCategoria categoria = EstanteCategoria.fromPortugues(nomeCategoria);
        List<Autor> autores = autorRepository.findByNomeContainingIgnoreCase(categoria.name());

        return converteDadosLivro(
                livroRepository.findByAutorIn(autores)
        );
    }

    // Converte a lista de livros para a lista de DTOs
    private List<LivroDTO> converteDadosLivro(List<Livro> livros) {
        return livros.stream()
            .map(l -> new LivroDTO(l.getId(), l.getTitulo(), l.getEditora(), l.getSinopse(), l.getDataLancamento(), l.getPoster(), l.getAutor().getNome()))
            .collect(Collectors.toList());
    }

    // Obtém autor por nome
    public AutorDTO obterAutorPorNome(String nome) {
        Optional<Autor> autorOpt = autorRepository.findByNomeIgnoreCase(nome);
        
        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            return new AutorDTO(autor.getId(), autor.getNome());
        }
        
        return null;
    }

    // Obtém todos os autores
    public List<AutorDTO> obterTodosAutores() {
        return autorRepository.findAll().stream()
            .map(a -> new AutorDTO(a.getId(), a.getNome()))
            .collect(Collectors.toList());
    }

    // Obtém livros por autor
    public List<LivroDTO> obterLivrosPorAutor(Long autorId) {
        Optional<Autor> autorOpt = autorRepository.findById(autorId);

        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            return converteDadosLivro(autor.getLivros());
        }

        return null;
    }

     // Obtém informações por livro específico
    public LivroDTO obterLivroPorId(Long Id) {
    return livroRepository.findById(Id)
            .map(livro -> new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getSinopse(),
                livro.getDataLancamento(),
                livro.getPoster(),
                livro.getAutor().getNome()
            ))
            .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + Id));
    }

}
