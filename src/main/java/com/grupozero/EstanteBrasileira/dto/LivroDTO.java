/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.grupozero.EstanteBrasileira.dto;

/**
 *
 * @author Marianna
 */
public record LivroDTO(Long Id,
                       String titulo,
                       String editora,
                       String sinopse,
                       String dataLancamento,
                       String poster,
                       String autor) {

}
