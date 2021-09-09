package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Opcao {

  private String texto;

  private boolean correto;
}
