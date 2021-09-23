package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class JogoController {

  public static final String UUID_PATH = "{uuid}";

  private final JogoService jogoService;

  private final RankingServiceImpl rankingServiceImpl;

  public JogoController(JogoService jogoService, RankingServiceImpl rankingServiceImpl) {
    this.jogoService = jogoService;
    this.rankingServiceImpl = rankingServiceImpl;
  }

  @PostMapping("novo-jogo")
  public JogoResponseDto criarJogo(@RequestBody String nome) {
    return this.jogoService.criarJogo(nome);
  }

  @PostMapping(UUID_PATH + "/responder")
  public JogoResponseDto postResposta(@PathVariable String uuid, @RequestBody int numero) {
    return this.jogoService.responder(uuid, numero);
  }

  @GetMapping(UUID_PATH + "/pular")
  public JogoResponseDto pular(@PathVariable String uuid) {
    return this.jogoService.pularAtual(uuid);
  }

  @GetMapping("ranking")
  public List<RankingResponseDto> getRanking() {
    return this.rankingServiceImpl.getRanking();
  }
}
