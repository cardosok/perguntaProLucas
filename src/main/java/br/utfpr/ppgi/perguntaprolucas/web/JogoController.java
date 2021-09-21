package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JogoController {

  private final JogoComponent jogoComponent;

  private final RankingServiceImpl rankingServiceImpl;

  public JogoController(JogoComponent jogoComponent, RankingServiceImpl rankingServiceImpl) {
    this.jogoComponent = jogoComponent;
    this.rankingServiceImpl = rankingServiceImpl;
  }

  @PostMapping("novo-jogo")
  public JogoResponseDto criarJogo(@RequestBody String nome) {
    return this.jogoComponent.criarJogo(nome);
  }

  @GetMapping("situacao-atual")
  public JogoResponseDto getSituacaoAtual() {
    return this.jogoComponent.getSituacaoAtual();
  }

  @PostMapping("responder")
  public JogoResponseDto postResposta(@RequestBody String letra) {
    return this.jogoComponent.responder(letra);
  }

  @GetMapping("ranking")
  public List<RankingResponseDto> getRanking() {
    return this.rankingServiceImpl.getRanking();
  }
}
