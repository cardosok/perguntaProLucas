package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.*;
import br.utfpr.ppgi.perguntaprolucas.infra.PerguntaServiceMockImpl;
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

  public static final int LETTER_A_ASCII_CODE = 97;

  private final RankingServiceImpl rankingServiceImpl;

  private App app;

  public JogoComponent(RankingServiceImpl rankingServiceImpl) {
    this.rankingServiceImpl = rankingServiceImpl;
  }

  public JogoResponseDto criarJogo(String nome) {
    log.info("Criando o novo jogo para {}", nome);
    this.app =
        App.builder()
            .categoriaSelecionada(new Categoria("Teste e Validação de Software"))
            .perguntaService(new PerguntaServiceMockImpl())
            .usuario(new Usuario(nome))
            .build();
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto getSituacaoAtual() {
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto responder(String letra) {
    val letraResposta = letra.charAt(0);
    int numeroResposta = letraResposta - LETTER_A_ASCII_CODE;
    val opcao = this.app.getPerguntaAtual().getOpcoes().get(numeroResposta);
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
