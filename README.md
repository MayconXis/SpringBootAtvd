# SpringBootAtvd
# API de Gerenciamento de Produtos

Este projeto é uma API REST para gerenciamento de produtos, desenvolvida utilizando Spring Boot, PostgreSQL, Flyway e Maven. A API permite realizar operações de CRUD (Criar, Ler, Atualizar e Deletar) para a entidade `Produto`.

##  Pré-requisitos

softwares utilizados 

- Java 
- PostgreSQL 
- Maven 
- IntelliJ IDEA 
- Postman 

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:
/src 
├── /main │

├── /java │ 

│ └── /com │ 

│ └── /SpringBoot │

 │ └── Produto.java # Classe que representa a entidade Produto │ 
 
 │ └── ProdutoRepositorio.java # Interface para operações de banco de dados │ 
 
 │ │ └── ProdutoServico.java # Classe de serviço para manipulação de produtos │ 
 
 │ └── ProdutoController.java # Controlador que expõe os endpoints da API │
 
 └── /resources │ ├── /db │ │ └── /application.properties # Scripts de migração do Flyway │ 
 
 │ └── criarTabela.sql # Script para criar a tabela de produtos │
 
---

## Funcionamento do Projeto

### 1. Entidade Produto

A classe `Produto` representa a entidade que será gerenciada pela API. Ela contém atributos como `id`, `nome`, `descricao`, `preco` e `quantidade`, além dos métodos getters e setters para manipulação dos dados.

### 2. Repositório

A interface `ProdutoRepositorio` estende `JpaRepository`, permitindo a realização de operações de CRUD no banco de dados de forma simples e eficiente.

### 3. Serviço

A classe `ProdutoServico` encapsula a lógica de negócios relacionada aos produtos. Ela fornece métodos para listar, buscar, salvar e deletar produtos, utilizando o repositório para interagir com o banco de dados.

### 4. Controlador

O `ProdutoController` é responsável por expor os endpoints da API. Ele utiliza a classe de serviço para realizar as operações solicitadas pelos clientes. Os endpoints permitem que os usuários interajam com a API para gerenciar produtos.

### 5. Migrações com Flyway

O Flyway é utilizado para gerenciar as migrações do banco de dados. Um script de migração é fornecido para criar a tabela `Produto` no banco de dados PostgreSQL. O Flyway executa automaticamente as migrações ao iniciar a aplicação, garantindo que a estrutura do banco de dados esteja sempre atualizada.

---

## Testando a API

Após a implementação da API, testei utilizando a ferramenta **Postman**. A API oferece endpoints para criar, listar, buscar e deletar produtos, permitindo uma interação completa com o sistema.

### Requisições

- **Criar um Produto:**
  ```bash
  curl -X POST http://localhost:8080/SpringBoot/produtos \
  -H "Content-Type: application/json" \
  -d '{
      "nome": "Produto ",
      "descricao": "Produto vermelho",
      "preco": 99.99,
      "quantidade": 10
  }'

- **Programação do Produto:**
   ### Produto.java
  
  ```bash
      
  package com.exemplo.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

 ### ProdutoServico.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {
    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public List<Produto> listarTodos() {
        return produtoRepositorio.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepositorio.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepositorio.findById(id).orElse(null);
    }


    public void deletar(Long id) {
        produtoRepositorio.deleteById(id);
    }
}
   ### ProdutoController.java
  
  ```bash
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SpringBoot/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoServico ProdutoServico;

    @GetMapping
    public List<Produto> listar() {
        return ProdutoServico.listarTodos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return ProdutoServico.salvar(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        Produto updatedProduto = ProdutoServico.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto produto = ProdutoServico.buscarPorId(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ProdutoServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}


   

  
