package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Ranking;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class RankingResponseDto {

    String usuario;

    Integer pontos;

    @JsonProperty("nivel")
    Dificuldade dificuldade;

    public static RankingResponseDto from(Ranking ranking) {
        return RankingResponseDto.builder()
                .usuario(ranking.getUsuario())
                .pontos(ranking.getPontos())
                .dificuldade(ranking.getDificuldade())
                .build();
    }

}
