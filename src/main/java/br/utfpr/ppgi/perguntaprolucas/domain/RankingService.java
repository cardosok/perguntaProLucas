package br.utfpr.ppgi.perguntaprolucas.domain;

import br.utfpr.ppgi.perguntaprolucas.web.RankingResponseDto;

import java.util.List;

public interface RankingService {

    void salvar(App app);

    List<RankingResponseDto> getRanking();
}
