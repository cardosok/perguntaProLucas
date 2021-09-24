package br.utfpr.ppgi.perguntaprolucas.web;

import lombok.Value;

@Value
public class NovoJogoRequest {

  String nome;

  String email;

  Integer categoriaId;
}
