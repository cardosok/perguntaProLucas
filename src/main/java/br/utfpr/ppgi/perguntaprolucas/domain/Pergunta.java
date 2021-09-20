package br.utfpr.ppgi.perguntaprolucas.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.val;

@Data
public class Pergunta {

  private TipoPergunta tipoPergunta;

  private String texto;

  private Dificuldade dificuldade;

  private Categoria categoria;

  private List<Opcao> opcoes;

  private Opcao opcaoSelecionada;

  @Builder
  public Pergunta(
      TipoPergunta tipoPergunta,
      String texto,
      Dificuldade dificuldade,
      Categoria categoria,
      @Singular("opcao") List<Opcao> opcoes) {
    this.tipoPergunta = tipoPergunta == null ? TipoPergunta.QUESTAO : tipoPergunta;
    this.texto = texto;
    this.dificuldade = dificuldade;
    this.categoria = categoria;
    this.opcoes = opcoes;
  }

  @Override
  public String toString() {
    val output = new StringBuilder();
    output.append(this.texto).append('\n');
    char letter = 'a';
    for (Opcao opc : this.opcoes) {
      output.append("\t").append(letter++).append(") - ").append(opc.getTexto()).append('\n');
    }
    return output.toString();
  }

  public void responder(Opcao opcaoSelecionada) {
    this.opcaoSelecionada = opcaoSelecionada;
  }
}
