package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import lombok.Value;

@Value
public class NovoJogoRequest {

  String nome;

  String email;

  Integer categoriaId;

}
