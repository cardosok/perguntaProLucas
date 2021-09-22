package br.utfpr.ppgi.perguntaprolucas.domain;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {

  List<Pergunta> findAllByCategoriaAndDificuldadeAndIdNotIn(
      Categoria categoria, Dificuldade dificuldade, Set<Integer> ids);
}
