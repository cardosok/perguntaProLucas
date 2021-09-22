package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.Opcao;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import br.utfpr.ppgi.perguntaprolucas.domain.TipoPergunta;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PerguntaDto {

  String texto;

  List<String> opcoes;

  TipoPergunta tipoPergunta;

  public static PerguntaDto from(Pergunta perguntaAtual) {
    return PerguntaDto.builder()
        .texto(perguntaAtual.getTexto())
        .tipoPergunta(perguntaAtual.getTipoPergunta())
        .opcoes(
            perguntaAtual.getOpcoes().stream().map(Opcao::getTexto).collect(Collectors.toList()))
        .build();
  }
}
