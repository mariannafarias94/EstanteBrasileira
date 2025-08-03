/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.grupozero.EstanteBrasileira.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Marianna
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("author") String autor) {

}
