package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.Opcao;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
@AllArgsConstructor
public class PerguntaDto {

    String texto;

    List<String> opcoes;

    public static PerguntaDto from(Pergunta perguntaAtual) {
        return PerguntaDto.builder()
                .texto(perguntaAtual.getTexto())
                .opcoes(perguntaAtual.getOpcoes()
                        .stream()
                        .map(Opcao::getTexto)
                        .collect(Collectors.toList()))
                .build();
    }

}
