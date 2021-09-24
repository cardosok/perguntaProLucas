package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.Ranking;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, Integer> {

  List<Ranking> findTop10ByOrderByPontosDesc();
}
