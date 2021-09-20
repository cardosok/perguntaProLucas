package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.val;

@Data
public class App {

  public static final int NUMERO_PERGUNTAS_VENCER = 20;

  public static final int PERGUNTAS_POR_DIFICULDADE = 5;

  public static final int MAX_RESPOSTAS_ERRADAS = 3;

  private Usuario usuario;

  private Categoria categoriaSelecionada;

  private Integer pontos;

  private Integer respostasCorretas;

  private Integer respostasCorretasDificuldade;

  private Integer respostasErradas;

  private Pergunta perguntaAtual;

  private Dificuldade dificuldadeAtual;

  private PerguntaService perguntaService;

  private boolean fimDoJogo = false;

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
    this.perguntaAtual = proximaPergunta();
  }

  public Pergunta proximaPergunta() {
    if (this.respostasErradas == MAX_RESPOSTAS_ERRADAS) {
      throw new JogoException("Você perdeu");
    }

    if (this.respostasCorretasDificuldade >= PERGUNTAS_POR_DIFICULDADE) {
      this.respostasCorretasDificuldade = 0;
      this.perguntaAtual =
          Pergunta.builder()
              .tipoPergunta(TipoPergunta.CONTINUAR)
              .texto(
                  String.format(
                      "Você deseja continuar para o nível %s?",
                      Dificuldade.values()[this.dificuldadeAtual.ordinal() + 1]))
              .opcao(Opcao.builder().texto("Sim").build())
              .opcao(Opcao.builder().texto("Não").build())
              .build();
    } else {
      this.perguntaAtual = this.perguntaService.proximaPergunta(this.dificuldadeAtual);
    }
    return this.perguntaAtual;
  }

  public boolean isRespostaCorreta(Opcao opcaoSelecionada) {
    this.perguntaAtual.responder(opcaoSelecionada);
    if (this.perguntaAtual.getTipoPergunta() == TipoPergunta.QUESTAO) {
      val correto = this.perguntaAtual.getOpcaoSelecionada().isCorreto();

      if (correto) {
        this.respostasCorretas++;
        this.respostasCorretasDificuldade++;
        this.pontos += this.perguntaAtual.getDificuldade().getPontos();
      } else {
        this.respostasErradas++;
      }
      this.perguntaAtual = proximaPergunta();
      return correto;
    } else if (this.perguntaAtual.getTipoPergunta() == TipoPergunta.CONTINUAR) {
      if (this.perguntaAtual.getOpcaoSelecionada().getTexto().contains("Sim")) {
        this.dificuldadeAtual = Dificuldade.values()[this.dificuldadeAtual.ordinal() + 1];
        this.perguntaAtual = proximaPergunta();
      } else {
        this.fimDoJogo = true;
      }
      return true;
    }
    throw new IllegalArgumentException("Tipo de pergunta desconhecido...");
  }
}
