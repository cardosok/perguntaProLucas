package br.utfpr.ppgi.perguntaprolucas;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.JogoException;
import br.utfpr.ppgi.perguntaprolucas.infra.PerguntaServiceMockImpl;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.val;

@SuppressWarnings("java:S106")
public class Main {

  public static final int LETTER_A_ASCII_CODE = 97;

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
            .categoriaSelecionada(new Categoria(1, "Categoria fake"))
            .perguntaService(new PerguntaServiceMockImpl())
            .build();

    System.out.println(
        "Olá "
            + usuario.getNome()
            + ", você está no nível "
            + app.getDificuldadeAtual()
            + " da categoria: "
            + app.getCategoriaSelecionada().getNome());

    while (!app.isFimDoJogo() && app.getRespostasCorretas() < App.NUMERO_PERGUNTAS_VENCER) {
      try {
        var perguntaAtual = app.getPerguntaAtual();
        cabecalho(app);
        System.out.println(perguntaAtual);
        System.out.println();
        System.out.println("Qual a sua opção?");
        int numeroResposta = lerReposta(reader);
        val opcaoSelecionada = perguntaAtual.getOpcoes().get(numeroResposta);

        if (app.isRespostaCorreta(opcaoSelecionada)) {
          System.out.println("\n ---> Certa a resposta!!!");
        } else {
          System.out.println("\n ---> Fraco hein!?");
        }
      } catch (JogoException e) {
        System.out.println("\nSeu nível é: " + app.getDificuldadeAtual());
        throw e;
      }
    }

    // TODO mostrar o resumo do cara
    System.out.println("Você venceu!");
  }

  private static void cabecalho(App app) {
    System.out.println();
    System.out.println("Status: ");
    System.out.println("\tPontos: .............: " + app.getPontos());
    System.out.println("\tRespostas corretas...: " + app.getRespostasCorretas());
    System.out.println("\tRespostas erradas....: " + app.getRespostasErradas());
    System.out.println("\tNível................: " + app.getDificuldadeAtual());
    System.out.println();
  }

  public static int lerReposta(BufferedReader reader) {
    while (true) {
      try {
        val resposta = reader.readLine();

        if (resposta.equalsIgnoreCase("\\q")) {
          throw new JogoException("Fim do jogo");
        }

        val letraResposta = resposta.charAt(0);
        int numeroResposta = letraResposta - LETTER_A_ASCII_CODE;

        if (numeroResposta < 0 || numeroResposta > 3) {
          System.out.println("Escolha uma opção entre: a, b, c ou d");
        } else {
          return numeroResposta;
        }
      } catch (IOException ex) {
        System.out.println("Erro ao ler a resposta selecionada: " + ex.getMessage());
      }
    }
  }
}
