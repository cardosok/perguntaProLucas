package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Teste {

  private App app;

  @BeforeEach
  void beforeEach() {
    this.app =
        App.builder()
            .usuario(new Usuario("Lucas"))
            .perguntaService(new PerguntaServiceMockImpl())
            .categoriaSelecionada(new Categoria("Engenharia de Software"))
            .build();
  }

  @Test
  @DisplayName("Testa o fluxo completo do jogo")
  void deveCriarJogo() {
    assertNotNull(this.app);
    assertNotNull(this.app.getUsuario());
    assertEquals("Engenharia de Software", this.app.getCategoriaSelecionada().getNome());
    assertEquals(0, this.app.getPontos());

    for (int i = 0; i < 15; i++) {
      val pergunta = this.app.getProximaPergunta();
      System.out.println(pergunta);
      assertTrue(this.app.isRespostaCorreta(pergunta.getOpcoes().get(0)));
    }
  }

  @Test
  @DisplayName("Testa o fluxo do jogo errando uma questao")
  void deveCriarJogo2() {
    assertNotNull(this.app);
    assertNotNull(this.app.getUsuario());
    assertEquals("Engenharia de Software", this.app.getCategoriaSelecionada().getNome());
    assertEquals(0, this.app.getPontos());

    // responde 7 corretas
    for (int i = 0; i < 7; i++) {
      val pergunta = this.app.getProximaPergunta();
      System.out.println(pergunta);
      assertTrue(this.app.isRespostaCorreta(pergunta.getOpcoes().get(0)));
      assertEquals(i + 1, this.app.getRespostasCorretas());
    }

    // responde 3 erradas
    for (int i = 0; i < 3; i++) {
      val pergunta = this.app.getProximaPergunta();
      System.out.println(pergunta);
      assertFalse(this.app.isRespostaCorreta(pergunta.getOpcoes().get(2)));
      assertEquals(i + 1, this.app.getRespostasErradas());
    }

    try {
      this.app.getProximaPergunta();
    } catch (JogoException ex) {
      assertEquals("Você perdeu", ex.getMessage());
    }
  }
}
