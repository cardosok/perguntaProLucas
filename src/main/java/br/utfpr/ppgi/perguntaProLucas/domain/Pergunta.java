package br.utfpr.ppgi.perguntaProLucas.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.val;

@Data
@Builder
public class Pergunta {

  private String texto;

  private Dificuldade dificuldade;

  private Categoria categoria;

  @Singular("opcao")
  private List<Opcao> opcoes;

  @Override
  public String toString() {
    val output = new StringBuilder("Nível: " + this.dificuldade + "\n");
    output.append(this.texto).append('\n');
    char letter = 'a';
    this.opcoes.forEach(
        opc ->
            output.append("\t").append(letter).append(") - ").append(opc.getTexto()).append('\n'));
    return output.toString();
  }
}
