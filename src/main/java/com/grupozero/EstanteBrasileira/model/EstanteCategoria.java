/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.grupozero.EstanteBrasileira.model;

/**
 *
 * @author faria
 */
public enum EstanteCategoria {

    JORGE_AMADO("Jorge Amado", "Jorge Amado"),
    GREGORIO_DE_MATOS("Gregório de Matos", "Gregório de Matos"),
    GONCALVES_DIAS("Gonçalves Dias", "Gonçalves Dias"),
    ALVARES_DE_AZEVEDO("Álvares de Azevedo", "Álvares de Azevedo"),
    CASTRO_ALVES("Castro Alves", "Castro Alves"),
    JOSE_DE_ALENCAR("José de Alencar", "José de Alencar"),
    BERNARDO_GUIMARAES("Bernardo Guimarães", "Bernardo Guimarães"),
    MACHADO_DE_ASSIS("Machado de Assis", "Machado de Assis"),
    ALUISIO_AZEVEDO("Aluísio Azevedo", "Aluísio Azevedo"),
    OLAVO_BILAC("Olavo Bilac", "Olavo Bilac"),
    MONTEIRO_LOBATO("Monteiro Lobato", "Monteiro Lobato"),
    CORA_CORALINA("Cora Coralina", "Cora Coralina"),
    MARIO_DE_ANDRADE("Mário de Andrade", "Mário de Andrade"),
    CARLOS_DRUMMOND_DE_ANDRADE("Carlos Drummond de Andrade", "Carlos Drummond de Andrade"),
    MARIO_QUINTANA("Mário Quintana", "Mário Quintana"),
    NELSON_RODRIGUES("Nelson Rodrigues", "Nelson Rodrigues"),
    GUIMARAES_ROSA("Guimarães Rosa", "Guimarães Rosa"),
    CAROLINA_MARIA_DE_JESUS("Carolina Maria de Jesus", "Carolina Maria de Jesus"),
    MANOEL_DE_BARROS("Manoel de Barros", "Manoel de Barros"),
    CLARICE_LISPECTOR("Clarice Lispector", "Clarice Lispector"),
    ARIANO_SUASSUNA("Ariano Suassuna", "Ariano Suassuna"),
    LUIS_FERNANDO_VERISSIMO("Luís Fernando Veríssimo", "Luís Fernando Veríssimo"),
    ZIRALDO("Ziraldo", "Ziraldo");

    private String nomeOriginal;
    private String nomePortugues;

    EstanteCategoria(String nomeOriginal, String nomePortugues) {
        this.nomeOriginal = nomeOriginal;
        this.nomePortugues = nomePortugues;
    }

    public static EstanteCategoria fromString(String text) {
        for (EstanteCategoria categoria : EstanteCategoria.values()) {
            if (categoria.nomeOriginal.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum autor encontrado para a string fornecida: " + text);
    }

    public static EstanteCategoria fromPortugues(String text) {
        for (EstanteCategoria categoria : EstanteCategoria.values()) {
            if (categoria.nomePortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum autor encontrado para a string fornecida: " + text);
    }
}
