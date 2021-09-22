package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Opcao;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PerguntaServiceMockImpl implements PerguntaService {

  private final Map<Dificuldade, List<Pergunta>> perguntas = new EnumMap<>(Dificuldade.class);

  public PerguntaServiceMockImpl() {

    int dificuldadeIndex = 0;
    Categoria categoria = new Categoria(1, "Categoria fake");
    for (int i = 0; i < Dificuldade.values().length; i++) {
      Dificuldade dificuldade = Dificuldade.values()[dificuldadeIndex++];
      if (!this.perguntas.containsKey(dificuldade)) {
        this.perguntas.put(dificuldade, new ArrayList<>());
      }
      for (int j = 0; j < 100; j++) {
        this.perguntas
            .get(dificuldade)
            .add(
                Pergunta.builder()
                    .categoria(categoria)
                    .texto("Texto da pergunta " + (j + 1))
                    .dificuldade(dificuldade)
                    .opcao(Opcao.builder().texto("Texto da opção 1").correto(true).build())
                    .opcao(Opcao.builder().texto("Texto da opção 2").correto(false).build())
                    .opcao(Opcao.builder().texto("Texto da opção 3").correto(false).build())
                    .opcao(Opcao.builder().texto("Texto da opção 4").correto(false).build())
                    .build());
      }
    }
  }

  @Override
  public Pergunta proximaPergunta(
      Categoria categoria, Dificuldade dificuldade, Set<Integer> idRespondidas) {
    return this.perguntas.get(dificuldade).get(new Random().nextInt(100));
  }
}
