package br.utfpr.ppgi.perguntaprolucas.domain;

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
    for (Opcao opc : this.opcoes) {
      output.append("\t").append(letter++).append(") - ").append(opc.getTexto()).append('\n');
    }
    return output.toString();
  }
}
