package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/pular")
  public JogoResponseDto pular() {
    return this.jogoComponent.pularAtual();
  }

  @GetMapping("ranking")
  public List<RankingResponseDto> getRanking() {
    return this.rankingServiceImpl.getRanking();
  }
}
