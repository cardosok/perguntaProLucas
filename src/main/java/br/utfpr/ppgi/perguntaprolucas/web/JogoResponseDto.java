package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class JogoResponseDto {

    String nome;

    String categoria;

    Integer pontos;

    Integer respostasCorretas;

    Integer respostasErradas;

    Dificuldade dificuldadeAtual;

    PerguntaDto perguntaAtual;

    Boolean respostaAnteriorCorreta;

    boolean fimDoJogo;

    public static JogoResponseDto from(App app) {
        return JogoResponseDto.builder()
                .nome(app.getUsuario().getNome())
                .categoria(app.getCategoriaSelecionada().getNome())
                .pontos(app.getPontos())
                .respostasCorretas(app.getRespostasCorretas())
                .respostasErradas(app.getRespostasErradas())
                .dificuldadeAtual(app.getDificuldadeAtual())
                .fimDoJogo(app.isFimDoJogo())
                .perguntaAtual(PerguntaDto.from(app.getPerguntaAtual()))
                .respostaAnteriorCorreta(app.getRespostaAnteriorCorreta())
                .build();
    }

}
