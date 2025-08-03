/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupozero.EstanteBrasileira.principal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupozero.EstanteBrasileira.dao.LivroDao;
import com.grupozero.EstanteBrasileira.model.DadosLivro;
import com.grupozero.EstanteBrasileira.model.Livro;
import com.grupozero.EstanteBrasileira.service.ConsumoApi;
import com.grupozero.EstanteBrasileira.service.ConverteDados;
import java.util.List;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.grupozero.EstanteBrasileira.dao.AutorDao;
import com.grupozero.EstanteBrasileira.model.Autor;
import com.grupozero.EstanteBrasileira.model.DadosAutor;
import com.grupozero.EstanteBrasileira.model.EstanteCategoria;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marianna
 */
@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.googleapis.com/books/v1/volumes?q=inauthor:";
    private final String API_KEY = "&key=AIzaSyA4I-7sLgTw9IQPGRpZGMq77nh3mFYlNdU";
    private LivroDao livroRepositorio;
    private AutorDao autorRepositorio;  // Declaração do AutorDao

public Principal(LivroDao livroRepositorio, AutorDao autorRepositorio) {
    this.livroRepositorio = livroRepositorio;
    this.autorRepositorio = autorRepositorio;
    
    
}    

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    1 - Buscar livros por autor
                    2 - Buscar livro por título
                    3 - Listar todos os livros
                    4 - Buscar livros por editora
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivrosPorAutor();
                case 2 -> buscarLivroPorTitulo();
                case 3 -> listarLivros();
                case 4 -> buscarLivrosPorEditora();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivrosPorAutor() {
    System.out.println("Digite o nome do Autor:");
    String nomeAutor = leitura.nextLine();

    // Verifica se o autor já existe no banco
    Autor autor = autorRepositorio.findByNomeIgnoreCase(nomeAutor).orElse(null);
    if (autor == null) {
        // Cria e salva a instância de Autor
        DadosAutor dadosAutor = new DadosAutor(nomeAutor);  // Cria os dados do autor
        autor = new Autor(dadosAutor);                      // Instancia o Autor
        autorRepositorio.save(autor);                       // Salva o autor no repositório
        System.out.println("Autor salvo: " + autor);
    } else {
        System.out.println("Autor já existente: " + autor);
    }

    // Obtém os dados dos livros associados ao autor
    List<DadosLivro> dadosLivros = getDadosLivros(nomeAutor);
    if (dadosLivros != null && !dadosLivros.isEmpty()) {
        for (DadosLivro dados : dadosLivros) {
            if (dados.titulo() != null && !dados.titulo().isEmpty()) {
                // Verifica se o livro já existe no repositório para o autor
                boolean livroJaExiste = livroRepositorio.existsByTituloAndAutor(dados.titulo(), autor);
                
                if (!livroJaExiste) {
                    Livro livro = new Livro(dados);
                    livro.setAutor(autor);                   // Define o autor do livro
                    
                     // Verifica se imageLinks e thumbnail não são nulos
                    if (dados.imageLinks() != null && dados.imageLinks().poster() != null) {
                        String thumbnailLink = dados.imageLinks().poster();  // Acessa o link do thumbnail
                        livro.setPoster(thumbnailLink);  // Salva o thumbnail no campo 'poster' do livro
                    }
                    
                    livroRepositorio.save(livro);            // Salva o livro no repositório
                    System.out.println("Livro salvo: " + livro);
                } else {
                    System.out.println("Livro já existente: " + dados.titulo());
                }
            } else {
                System.out.println("Erro: Título não encontrado ou inválido para o livro.");
            }
        }
    } else {
        System.out.println("Nenhum livro encontrado para o autor.");
    }
}

private List<DadosLivro> getDadosLivros(String nomeAutor) {
    try {
        // Obtenha os dados da API
        var json = consumo.obterDados(ENDERECO + nomeAutor.replace(" ", "+") + API_KEY);

        // Crie uma instância do ObjectMapper do Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Faça o parsing do JSON para um nó JSON
        JsonNode rootNode = objectMapper.readTree(json);

        // Verifique se existe o campo "items" no JSON
        if (rootNode.has("items") && rootNode.get("items").isArray()) {
            JsonNode items = rootNode.get("items");

            // Lista para armazenar os livros
            List<DadosLivro> listaLivros = new ArrayList<>();

            // Itera sobre todos os itens no array "items"
            for (JsonNode itemNode : items) {
                JsonNode volumeInfo = itemNode.get("volumeInfo");

                // Utilizando Jackson para mapear diretamente para DadosLivro
                DadosLivro dadosLivro = objectMapper.treeToValue(volumeInfo, DadosLivro.class);

                              
                // Adiciona o livro à lista
                listaLivros.add(dadosLivro);
            }

            // Exibe a lista de livros
            System.out.println("Dados dos livros: " + listaLivros);
            return listaLivros;
        } else {
            throw new IllegalArgumentException("Nenhum livro encontrado para o autor: " + nomeAutor);
        }

    } catch (Exception e) {
        System.out.println("Erro ao buscar dados do livro: " + e.getMessage());
        return null;  // Retorna null em caso de erro
    }
}
    
    private void buscarLivroPorTitulo() {
        System.out.println("Digite o Título do Livro:");
        String titulo = leitura.nextLine();

        List<Livro> livrosEncontrados = livroRepositorio.findByTituloContainingIgnoreCase(titulo);
        if (!livrosEncontrados.isEmpty()) {
            livrosEncontrados.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado para o título especificado.");
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroRepositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void buscarLivrosPorEditora() {
        System.out.println("Digite o nome da editora:");
        String editora = leitura.nextLine();

        List<Livro> livrosPorEditora = livroRepositorio.findByEditoraContainingIgnoreCase(editora);
        if (!livrosPorEditora.isEmpty()) {
            livrosPorEditora.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado para a editora especificada.");
        }
    }
}