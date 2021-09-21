package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Ranking;
import br.utfpr.ppgi.perguntaprolucas.domain.RankingRepository;
import br.utfpr.ppgi.perguntaprolucas.domain.RankingService;
import br.utfpr.ppgi.perguntaprolucas.web.RankingResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RankingServiceImpl implements RankingService {

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
