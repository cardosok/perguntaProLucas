package br.utfpr.ppgi.perguntaprolucas.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

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
  @SuppressWarnings("unused")
  public Ranking(String usuario, Integer pontos, Dificuldade dificuldade) {
    this.usuario = usuario;
    this.pontos = pontos;
    this.dificuldade = dificuldade;
  }

  protected Ranking() {
    // required by JPA
  }
}
