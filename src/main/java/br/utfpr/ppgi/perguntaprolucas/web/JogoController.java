package br.utfpr.ppgi.perguntaprolucas.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JogoController {

  private final JogoComponent jogoComponent;

  public JogoController(JogoComponent jogoComponent) {
    this.jogoComponent = jogoComponent;
  }

  @GetMapping("/novo-jogo/{nome}")
  public void criarJogo(@PathVariable("nome") String nome) {
    this.jogoComponent.criarJogo(nome);
  }

  @GetMapping("/situacao-atual")
  public String getSituacaoAtual() {
    return this.jogoComponent.getSituacaoAtual();
  }
}
