package br.utfpr.ppgi.perguntaprolucas.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
public class App {

  public static final int NUMERO_PERGUNTAS_VENCER = 20;

  public static final int PERGUNTAS_POR_DIFICULDADE = 3;

  public static final int MAX_RESPOSTAS_ERRADAS = 3;

  public static final String SIM = "Sim";

  public static final String NAO = "Não";

  public static final long DURACAO_MAXIMA_JOGO = 5L;

  private LocalDateTime dataHoraCriacao;

  private Usuario usuario;

  private Categoria categoriaSelecionada;

  private Integer pontos;

  private Integer respostasCorretas;

  private Integer respostasCorretasDificuldade;

  private Integer respostasErradas;

  private Pergunta perguntaAtual;

  private Dificuldade dificuldadeAtual;

  private boolean fimDoJogo = false;

  private Boolean respostaAnteriorCorreta;

  private PerguntaService perguntaService;

  private int pulos = 3;

  private Set<Integer> idRespondidas;

  private String uuid;

  @Builder
  public App(PerguntaService perguntaService, Usuario usuario, Categoria categoriaSelecionada) {
    this.perguntaService = perguntaService;
    this.usuario = usuario;
    this.categoriaSelecionada = categoriaSelecionada;
    this.dificuldadeAtual = Dificuldade.INICIANTE;
    this.pontos = 0;
    this.respostasCorretas = 0;
    this.respostasCorretasDificuldade = 0;
    this.respostasErradas = 0;
    this.idRespondidas = new HashSet<>(Collections.singletonList(0));
    this.respostaAnteriorCorreta = null;
    this.perguntaAtual = proximaPergunta();
    this.dataHoraCriacao = LocalDateTime.now();
  }

  public void pularAtual() {
    if (this.pulos == 0) {
      return;
    }

    this.pulos--;
    this.perguntaAtual = encontrarPergunta();
  }

  private Pergunta encontrarPergunta() {
    return this.perguntaService.proximaPergunta(
        this.categoriaSelecionada, this.dificuldadeAtual, this.idRespondidas);
  }

  public Pergunta proximaPergunta() {
    if (this.respostasErradas == MAX_RESPOSTAS_ERRADAS) {
      this.fimDoJogo = true;
      throw new JogoException("Você perdeu");
    }

    if (isJogoTerminado()) {
      this.fimDoJogo = true;
      return perguntaDeFimDeJogo();
    }

    if (this.respostasCorretasDificuldade >= PERGUNTAS_POR_DIFICULDADE) {
      this.respostasCorretasDificuldade = 0;
      this.perguntaAtual = perguntaDeMudancaDeNivel();
    } else {
      this.perguntaAtual = encontrarPergunta();
    }
    return this.perguntaAtual;
  }

  private Pergunta perguntaDeFimDeJogo() {
    return Pergunta.builder()
        .categoria(this.categoriaSelecionada)
        .dificuldade(this.dificuldadeAtual)
        .texto("Você venceu!!!")
        .opcao(Opcao.builder().texto("Encerrar").build())
        .build();
  }

  private Pergunta perguntaDeMudancaDeNivel() {
    return Pergunta.builder()
        .tipoPergunta(TipoPergunta.CONTINUAR)
        .texto(
            String.format("Você deseja continuar para o nível %s?", getProximoNivelDificuldade()))
        .opcao(Opcao.builder().texto(SIM).build())
        .opcao(Opcao.builder().texto(NAO).build())
        .build();
  }

  private Dificuldade getProximoNivelDificuldade() {
    return Dificuldade.values()[this.dificuldadeAtual.ordinal() + 1];
  }

  private boolean isJogoTerminado() {
    return this.dificuldadeAtual.ordinal()
            == Dificuldade.values()[Dificuldade.values().length - 1].ordinal()
        && this.respostasCorretasDificuldade == PERGUNTAS_POR_DIFICULDADE;
  }

  // TODO refatorar esse método
  public boolean isRespostaCorreta(Opcao opcaoSelecionada) {
    this.perguntaAtual.responder(opcaoSelecionada);

    if (this.perguntaAtual.getTipoPergunta() == TipoPergunta.QUESTAO) {
      this.idRespondidas.add(this.perguntaAtual.getId());
      this.respostaAnteriorCorreta = this.perguntaAtual.getOpcaoSelecionada().isCorreto();

      if (Boolean.TRUE.equals(this.respostaAnteriorCorreta)) {
        this.respostasCorretas++;
        this.respostasCorretasDificuldade++;
        this.pontos += this.perguntaAtual.getDificuldade().getPontos();
      } else {
        this.respostasErradas++;
      }
      this.perguntaAtual = proximaPergunta();
      return this.respostaAnteriorCorreta;
    } else if (this.perguntaAtual.getTipoPergunta() == TipoPergunta.CONTINUAR) {
      this.respostaAnteriorCorreta = null;
      if (this.perguntaAtual.getOpcaoSelecionada().getTexto().contains(SIM)) {
        this.dificuldadeAtual = getProximoNivelDificuldade();
        this.perguntaAtual = proximaPergunta();
      } else {
        this.fimDoJogo = true;
      }
      return true;
    }
    throw new IllegalArgumentException("Tipo de pergunta desconhecido...");
  }

  public boolean isExpirado() {
    return ChronoUnit.MINUTES.between(LocalDateTime.now(), this.dataHoraCriacao)
        > DURACAO_MAXIMA_JOGO;
  }
}
