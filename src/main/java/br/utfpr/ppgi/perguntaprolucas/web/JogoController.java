package br.utfpr.ppgi.perguntaprolucas.web;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JogoController {

  private final JogoComponent jogoComponent;

  private final RankingService rankingService;

  public JogoController(JogoComponent jogoComponent, RankingService rankingService) {
    this.jogoComponent = jogoComponent;
    this.rankingService = rankingService;
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
    return this.rankingService.getRanking();
  }
}
