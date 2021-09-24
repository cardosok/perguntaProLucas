package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
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

  private final JogoFacade jogoFacade;

  private final RankingServiceImpl rankingServiceImpl;

  public JogoController(JogoFacade jogoFacade, RankingServiceImpl rankingServiceImpl) {
    this.jogoFacade = jogoFacade;
    this.rankingServiceImpl = rankingServiceImpl;
  }

  @PostMapping("novo-jogo")
  public JogoResponseDto criarJogo(@RequestBody NovoJogoRequest novoJogoRequest) {
    return this.jogoFacade.criarJogo(novoJogoRequest);
  }

  @PostMapping(UUID_PATH + "/responder")
  public JogoResponseDto postResposta(@PathVariable String uuid, @RequestBody int numero) {
    return this.jogoFacade.responder(uuid, numero);
  }

  @GetMapping(UUID_PATH + "/pular")
  public JogoResponseDto pular(@PathVariable String uuid) {
    return this.jogoFacade.pularAtual(uuid);
  }

  @GetMapping("ranking")
  public List<RankingResponseDto> getRanking() {
    return this.rankingServiceImpl.getRanking();
  }

  @GetMapping("categorias")
  public List<Categoria> getCategorias() {
    return this.jogoFacade.getCategorias();
  }


}
