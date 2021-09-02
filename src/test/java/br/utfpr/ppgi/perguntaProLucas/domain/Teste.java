package br.utfpr.ppgi.perguntaProLucas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Teste {

  @Test
  @DisplayName("Testa o fluxo completo do jogo")
  void deveCriarJogo() {
    val app =
        App.builder()
            .usuario(new Usuario("Lucas"))
            .perguntaService(new PerguntaServiceMockImpl())
            .categoriaSelecionada(new Categoria("Engenharia de Software"))
            .build();

    assertNotNull(app);
    assertNotNull(app.getUsuario());
    assertEquals("Engenharia de Software", app.getCategoriaSelecionada().getNome());
    assertEquals(0, app.getPontos());

    for (int i = 0; i < 15; i++) {
      val pergunta = app.getProximaPergunta();
      System.out.println(pergunta);
      assertTrue(app.isRespostaCorreta(pergunta.getOpcoes().get(0)));
    }
  }
}
