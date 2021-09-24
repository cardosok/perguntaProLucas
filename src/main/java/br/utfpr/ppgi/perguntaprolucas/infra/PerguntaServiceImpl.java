package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import java.util.Random;
import java.util.Set;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class PerguntaServiceImpl implements PerguntaService {

  private final PerguntaRepository perguntaRepository;

  public PerguntaServiceImpl(PerguntaRepository perguntaRepository) {
    this.perguntaRepository = perguntaRepository;
  }

  @Override
  public Pergunta proximaPergunta(
      Categoria categoria, Dificuldade dificuldade, Set<Integer> idRespondidas) {
    val perguntas =
        this.perguntaRepository.findAllByCategoriaAndDificuldadeAndIdNotIn(
            categoria, dificuldade, idRespondidas);
    return perguntas.get(new Random().nextInt(perguntas.size()));
  }
}
