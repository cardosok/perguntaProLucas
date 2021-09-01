package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class App {

  private Usuario usuario;

  private Integer pontos;

  private Integer perguntasRespondidas;

  @Builder
  public App(Usuario usuario) {
    this.usuario = usuario;
    this.pontos = 0;
    this.perguntasRespondidas = 0;
  }
}
