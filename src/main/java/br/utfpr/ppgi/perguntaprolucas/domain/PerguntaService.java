package br.utfpr.ppgi.perguntaprolucas.domain;

import java.util.Set;

public interface PerguntaService {

  Pergunta proximaPergunta(
      Categoria categoria, Dificuldade dificuldade, Set<Integer> idRespondidas);
}
