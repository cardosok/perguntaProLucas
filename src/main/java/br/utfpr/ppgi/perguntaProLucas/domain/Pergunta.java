package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class Pergunta {

  private String texto;

  private Dificuldade dificuldade;

  private Categoria categoria;

  @Singular("opcao")
  private List<Opcao> opcoes;

}
