package br.utfpr.ppgi.perguntaprolucas.domain;

import br.utfpr.ppgi.perguntaprolucas.domain.Ranking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RankingRepository extends CrudRepository<Ranking, Integer> {

    List<Ranking> findTop10ByOrderByPontosDesc();

}
