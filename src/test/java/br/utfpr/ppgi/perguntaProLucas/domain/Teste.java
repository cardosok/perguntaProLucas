package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Teste {

  @Test
  void deveCriarJogo() {
    val app = App.builder().usuario(new Usuario("Lucas")).build();
    assertNotNull(app);
    assertNotNull(app.getUsuario());
    assertEquals(0, app.getPontos());
  }

}
