/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupozero.EstanteBrasileira.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.antlr.v4.runtime.misc.NotNull;

/**
 *
 * @author Marianna
 */
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull  // Ensure that the field cannot be null
     @Column(name = "titulo", nullable = false, length = 500)
    private String titulo;

    @Column(name = "editora", nullable = true, length = 255)
    private String editora;

    @Column(name = "sinopse", nullable = true, columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "data_lancamento", nullable = true)
    private String dataLancamento;  // Mantendo como String
    
    @Column(name = "poster", nullable = true, columnDefinition = "TEXT")
    private String poster;

    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.editora = dadosLivro.editora();
        this.sinopse = dadosLivro.sinopse();
        this.dataLancamento = dadosLivro.dataLancamento();  // Mantendo como String
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", autor=" + (autor != null ? autor.getNome() : "N/A") +
                ", poster='" + poster + '\'' +
                '}';
    }
}