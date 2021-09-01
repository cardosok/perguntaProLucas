package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Opcao {

  private String texto;

  private boolean correto;
}
