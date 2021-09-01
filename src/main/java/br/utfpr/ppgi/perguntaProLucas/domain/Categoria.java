package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Data;

@Data
public class Categoria {

  private String nome;

  private String descricao;

  public Categoria(String nome) {
    this.nome = nome;
  }

}
