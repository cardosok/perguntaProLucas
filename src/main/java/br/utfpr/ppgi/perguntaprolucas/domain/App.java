package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.val;

@Data
public class App {

  public static final int NUMERO_PERGUNTAS_VENCER = 15;

  public static final int PERGUNTAS_POR_DIFICULDADE = 5;

  public static final int MAX_RESPOSTAS_ERRADAS = 3;

  private Usuario usuario;

  private Categoria categoriaSelecionada;

  private Integer pontos;

  private Integer respostasCorretas;

  private Integer respostasErradas;

  private Pergunta perguntaAtual;

  private Dificuldade dificuldadeAtual;

  private PerguntaService perguntaService;

  @Builder
  public App(PerguntaService perguntaService, Usuario usuario, Categoria categoriaSelecionada) {
    this.perguntaService = perguntaService;
    this.usuario = usuario;
    this.categoriaSelecionada = categoriaSelecionada;
    this.dificuldadeAtual = Dificuldade.INICIANTE;
    this.pontos = 0;
    this.respostasCorretas = 0;
    this.respostasErradas = 0;
  }

  public Pergunta getProximaPergunta() {
    if (this.respostasErradas == MAX_RESPOSTAS_ERRADAS) {
      throw new JogoException("Você perdeu");
    }

    if (this.respostasCorretas > 0 && this.respostasCorretas % PERGUNTAS_POR_DIFICULDADE == 0) {
      this.dificuldadeAtual = Dificuldade.values()[this.dificuldadeAtual.ordinal() + 1];
    }

    this.perguntaAtual = this.perguntaService.proximaPergunta(this.dificuldadeAtual);
    return this.perguntaAtual;
  }

  public boolean isRespostaCorreta(Opcao opcaoSelecionada) {
    val correto = opcaoSelecionada.isCorreto();
    if (correto) {
      this.respostasCorretas++;
      this.pontos += this.perguntaAtual.getDificuldade().getPontos();
    } else {
      this.respostasErradas++;
    }
    return correto;
  }
}
