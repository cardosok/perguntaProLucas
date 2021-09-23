package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JogoComponent {

  private final RankingServiceImpl rankingServiceImpl;

  private final PerguntaService perguntaService;

  private App app;

  public JogoComponent(RankingServiceImpl rankingServiceImpl, PerguntaService perguntaService) {
    this.rankingServiceImpl = rankingServiceImpl;
    this.perguntaService = perguntaService;
  }

  public JogoResponseDto criarJogo(String nome) {
    log.info("Criando o novo jogo para {}", nome);
    this.app =
        App.builder()
            .categoriaSelecionada(new Categoria(1, "Teste e Validação de Software"))
            .perguntaService(this.perguntaService)
            .usuario(new Usuario(nome))
            .build();
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto getSituacaoAtual() {
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto pularAtual() {
    this.app.pularAtual();
    return JogoResponseDto.from(this.app);
  }

  public JogoResponseDto responder(int numero) {
    val opcao = this.app.getPerguntaAtual().getOpcoes().get(numero);
    try {
      this.app.isRespostaCorreta(opcao);
    } finally {
      if (this.app.isFimDoJogo()) {
        rankingServiceImpl.salvar(this.app);
      }
    }
    return JogoResponseDto.from(this.app);
  }
}
