package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.val;

@Data
public class App {

  private Usuario usuario;

  private Categoria categoriaSelecionada;

  private Integer pontos;

  private Integer perguntasRespondidas;

  private Pergunta perguntaAtual;

  @Builder
  public App(Usuario usuario, Categoria categoriaSelecionada) {
    this.usuario = usuario;
    this.categoriaSelecionada = categoriaSelecionada;
    this.pontos = 0;
    this.perguntasRespondidas = 0;
  }

  public Pergunta getProximaPergunta() {
    perguntaAtual =
        Pergunta.builder()
            .texto("Pergunta 1")
            .categoria(categoriaSelecionada)
            .dificuldade(Dificuldade.JUNIOR)
            .opcao(Opcao.builder().texto("Resposta 1").correto(false).build())
            .opcao(Opcao.builder().texto("Resposta 2").correto(false).build())
            .opcao(Opcao.builder().texto("Resposta 3").correto(true).build())
            .opcao(Opcao.builder().texto("Resposta 4").correto(false).build())
            .build();
    return perguntaAtual;
  }

  public boolean isRespostaCorreta(Opcao opcaoSelecionada) {
    perguntasRespondidas++;
    val correto = opcaoSelecionada.isCorreto();
    if (correto) {
      pontos++;
    }
    return correto;
  }
}
