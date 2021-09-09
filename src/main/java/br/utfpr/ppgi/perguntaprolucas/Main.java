package br.utfpr.ppgi.perguntaprolucas;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.JogoException;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaServiceMockImpl;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.val;

public class Main {

  @SuppressWarnings("java:S106")
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Bem-vindo ao jogo Pergunta pro Lucas");
    System.out.println("v0.1 - teste");

    System.out.println("Digite seu nome: ");
    val nome = reader.readLine();
    val usuario = new Usuario(nome);

    val app =
        App.builder()
            .usuario(usuario)
            .categoriaSelecionada(new Categoria("Categoria fake"))
            .perguntaService(new PerguntaServiceMockImpl())
            .build();

    System.out.println(
        "Olá "
            + usuario.getNome()
            + ", você está no nível "
            + app.getDificuldadeAtual()
            + " da categoria: "
            + app.getCategoriaSelecionada().getNome());

    while (true) {
      try {
        var perguntaAtual = app.getProximaPergunta();
        cabecalho(app);
        System.out.println(perguntaAtual);
        System.out.println();
        System.out.println("Qual a sua opção?");
        val letraResposta = reader.readLine().charAt(0);
        int numeroResposta = letraResposta - 97;
        val opcaoSelecionada = perguntaAtual.getOpcoes().get(numeroResposta);

        if (app.isRespostaCorreta(opcaoSelecionada)) {
          System.out.println("\nResposta certa!");
        } else {
          System.out.println("\nReposta errada!");
        }
      } catch (JogoException e) {
        System.out.println("\nSeu nível é: " + app.getDificuldadeAtual());
        throw e;
      }
    }
  }

  private static void cabecalho(App app) {
    System.out.println();
    System.out.println("Status: ");
    System.out.println("\tPontos: .............:" + app.getPontos());
    System.out.println("\tRespostas corretas...: " + app.getRespostasCorretas());
    System.out.println("\tRespostas erradas....: " + app.getRespostasErradas());
    System.out.println("\tNível................: " + app.getDificuldadeAtual());
    System.out.println();
  }
}
