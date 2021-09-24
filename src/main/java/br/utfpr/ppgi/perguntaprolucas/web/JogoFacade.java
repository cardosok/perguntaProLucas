package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.CategorigaService;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import br.utfpr.ppgi.perguntaprolucas.infra.RankingServiceImpl;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JogoFacade {

  private final JogoAtivoComponent jogoAtivoComponent;

  private final RankingServiceImpl rankingServiceImpl;

  private final PerguntaService perguntaService;

  private final CategorigaService categorigaService;

  public JogoFacade(
      JogoAtivoComponent jogoAtivoComponent,
      RankingServiceImpl rankingServiceImpl,
      PerguntaService perguntaService,
      CategorigaService categorigaService) {
    this.jogoAtivoComponent = jogoAtivoComponent;
    this.rankingServiceImpl = rankingServiceImpl;
    this.perguntaService = perguntaService;
    this.categorigaService = categorigaService;
  }

  public JogoResponseDto criarJogo(NovoJogoRequest novoJogoRequest) {
    log.info("Criando o novo jogo para {}", novoJogoRequest.getNome());
    val app =
        App.builder()
            .categoriaSelecionada(
                this.categorigaService.getCategoria(novoJogoRequest.getCategoriaId()))
            .perguntaService(this.perguntaService)
            .usuario(new Usuario(novoJogoRequest.getNome()))
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

  public List<Categoria> getCategorias() {
    return this.categorigaService.getCategorias();
  }
}
