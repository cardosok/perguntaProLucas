package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Ranking;
import br.utfpr.ppgi.perguntaprolucas.infra.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RankingService {

    private final RankingRepository repository;

    public void salvar(App app) {
        Ranking ranking = Ranking.builder()
                .usuario(app.getUsuario().getNome())
                .pontos(app.getPontos())
                .dificuldade(app.getDificuldadeAtual())
                .build();
        repository.save(ranking);
    }

    public List<RankingResponseDto> getRanking() {
        return repository.findTop10ByOrderByPontosDesc()
                .stream()
                .map(RankingResponseDto::from)
                .collect(Collectors.toList());
    }

}
