package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

  @Id
  private Integer id;

  private String nome;

  private String descricao;

  public Categoria(String nome) {
    this.nome = nome;
  }

}
