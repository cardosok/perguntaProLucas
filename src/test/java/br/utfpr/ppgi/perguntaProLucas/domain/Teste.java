package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Teste {

  @Test
  void deveCriarJogo() {
    val app =
        App.builder()
            .usuario(new Usuario("Lucas"))
            .categoriaSelecionada(new Categoria("Engenharia de Software"))
            .build();

    assertNotNull(app);
    assertNotNull(app.getUsuario());
    assertEquals("Engenharia de Software", app.getCategoriaSelecionada().getNome());
    assertEquals(0, app.getPontos());

    val pergunta = app.getProximaPergunta();

    assertTrue(app.isRespostaCorreta(pergunta.getOpcoes().get(2)));
  }
}
