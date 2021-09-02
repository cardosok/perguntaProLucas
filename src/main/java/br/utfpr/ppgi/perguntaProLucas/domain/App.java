package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.val;

@Data
public class App {

  private static final int NUMERO_PERGUNTAS_VENCER = 15;

  private static final int PERGUNTAS_POR_DIFICULDADE = 5;

  private Usuario usuario;

  private Categoria categoriaSelecionada;

  private Integer pontos;

  private Integer perguntasRespondidas;

  private Pergunta perguntaAtual;

  private Dificuldade dificuldadeAtual;

  private PerguntaService perguntaService;

  @Builder
  public App(PerguntaService perguntaService, Usuario usuario, Categoria categoriaSelecionada) {
    this.perguntaService = perguntaService;
    this.usuario = usuario;
    this.categoriaSelecionada = categoriaSelecionada;
    this.dificuldadeAtual = Dificuldade.JUNIOR;
    this.pontos = 0;
    this.perguntasRespondidas = 0;
  }

  public Pergunta getProximaPergunta() {
    if (this.perguntasRespondidas > 0
        && this.perguntasRespondidas % PERGUNTAS_POR_DIFICULDADE == 0) {
      this.dificuldadeAtual = Dificuldade.values()[this.dificuldadeAtual.ordinal() + 1];
    }
    this.perguntaAtual = this.perguntaService.proximaPergunta(this.dificuldadeAtual);
    return this.perguntaAtual;
  }

  public boolean isRespostaCorreta(Opcao opcaoSelecionada) {
    this.perguntasRespondidas++;
    val correto = opcaoSelecionada.isCorreto();
    if (correto) {
      pontos += this.perguntaAtual.getDificuldade().getPontos();
    }
    return correto;
  }
}
