
# EstanteBrasileira

## 📚 Descrição

EstanteBrasileira é uma aplicação backend desenvolvida em Java com Spring Boot que permite buscar, cadastrar e listar livros e autores. A aplicação consome dados da API do Google Books para enriquecer as informações dos livros e organiza autores e seus respectivos livros em um banco de dados relacional.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- JPA / Hibernate
- Banco de Dados relacional (ex: H2, MySQL, etc. — ajuste conforme seu uso)
- Jackson (para processamento JSON)
- API Google Books (para obtenção de dados externos)

---

## 🔍 Funcionalidades

- Busca de livros por autor, título e editora
- Cadastro automático de autores e livros com dados da API Google Books
- Listagem de todos os livros e autores cadastrados
- Endpoints REST para consulta dos dados

---

## 📁 Estrutura do Projeto

- **com.grupozero.EstanteBrasileira.principal.Principal**: Classe principal para execução local via terminal com menu interativo  
- **com.grupozero.EstanteBrasileira.controller.EstanteController**: Controlador REST que expõe os endpoints para acesso externo  
- **com.grupozero.EstanteBrasileira.service.EstanteService**: Serviço que encapsula a lógica de negócio  
- **com.grupozero.EstanteBrasileira.dao / repository**: Interfaces para acesso a dados via JPA  
- **com.grupozero.EstanteBrasileira.model**: Classes de entidade e DTOs para mapeamento do banco de dados e transferência de dados  
- **com.grupozero.EstanteBrasileira.service.ConsumoApi**: Classe para consumir dados HTTP da API Google Books  
- **com.grupozero.EstanteBrasileira.service.ConverteDados**: Classe utilitária para converter JSON em objetos Java  

---

## 🧪 Como Rodar

1. Clone o repositório:

```bash
git clone https://github.com/mariannafarias94/EstanteBrasileira.git
cd EstanteBrasileira
```

2. Compile e execute o projeto com sua IDE favorita (ex: IntelliJ, VSCode) ou via terminal com:

```bash
./mvnw spring-boot:run
```

3. Acesse a API em:  
   `http://localhost:8080`

---

## 🔧 Próximos Passos / Melhorias

- Implementar autenticação e autorização  
- Criar frontend com interface gráfica (ex: Angular, React ou JavaFX)  
- Melhorar tratamento de erros e mensagens  
- Suporte para mais filtros e pesquisa avançada  

---

## 👤 Contato

Desenvolvido por Marianna Farias  
Email: mariannafarias94@gmail.com  
LinkedIn: [linkedin.com/in/marianna-lucia-farias-monteiro-3850352554](www.linkedin.com/in/marianna-lucia-farias-monteiro-3850352554)

---

## 📄 Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).

