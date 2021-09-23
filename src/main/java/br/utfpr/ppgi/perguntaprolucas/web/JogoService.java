package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JogoService {

  private final JogoAtivoComponent jogoAtivoComponent;

  private final RankingServiceImpl rankingServiceImpl;

  private final PerguntaService perguntaService;

  public JogoService(
      JogoAtivoComponent jogoAtivoComponent,
      RankingServiceImpl rankingServiceImpl,
      PerguntaService perguntaService) {
    this.jogoAtivoComponent = jogoAtivoComponent;
    this.rankingServiceImpl = rankingServiceImpl;
    this.perguntaService = perguntaService;
  }

  public JogoResponseDto criarJogo(String nome) {
    log.info("Criando o novo jogo para {}", nome);
    val app =
        App.builder()
            .categoriaSelecionada(new Categoria(1, "Teste e Validação de Software"))
            .perguntaService(this.perguntaService)
            .usuario(new Usuario(nome))
            .build();
    this.jogoAtivoComponent.putJogo(UUID.randomUUID().toString(), app);
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto pularAtual(String uuid) {
    val app = this.jogoAtivoComponent.getJogo(uuid);
    app.pularAtual();
    return JogoResponseDto.from(app);
  }

  public JogoResponseDto responder(String uuid, int numero) {
    val app = this.jogoAtivoComponent.getJogo(uuid);
    val opcao = app.getPerguntaAtual().getOpcoes().get(numero);
    try {
      app.isRespostaCorreta(opcao);
    } finally {
      if (app.isFimDoJogo()) {
        rankingServiceImpl.salvar(app);
      }
    }
    return JogoResponseDto.from(app);
  }
}
