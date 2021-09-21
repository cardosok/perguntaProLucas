package br.utfpr.ppgi.perguntaprolucas.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JogoController {

  private final JogoComponent jogoComponent;

  public JogoController(JogoComponent jogoComponent) {
    this.jogoComponent = jogoComponent;
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
}
