package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ranking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String usuario;

  private Integer pontos;

  @Enumerated(EnumType.STRING)
  private Dificuldade dificuldade;

  @Builder
  public Ranking(String usuario, Integer pontos, Dificuldade dificuldade) {
    this.usuario = usuario;
    this.pontos = pontos;
    this.dificuldade = dificuldade;
  }

  protected Ranking() {
    // required by JPA
  }

}
