/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.grupozero.EstanteBrasileira.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
/**
 *
 * @author Marianna
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<String> autor,
        @JsonAlias("publisher") String editora,
        @JsonAlias("description") String sinopse,
        @JsonAlias("publishedDate") String dataLancamento,
        @JsonAlias("imageLinks") ImageLinks imageLinks) { // Adiciona o campo ImageLinks

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ImageLinks(
        @JsonAlias("thumbnail") String poster // Aqui é onde estamos armazenando o link do thumbnail
) {
}
}