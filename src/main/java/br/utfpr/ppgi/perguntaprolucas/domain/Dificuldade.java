package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Dificuldade {
  JUNIOR(100),
  PLENO(200),
  SENIOR(300);

  @Getter private final int pontos;
}
