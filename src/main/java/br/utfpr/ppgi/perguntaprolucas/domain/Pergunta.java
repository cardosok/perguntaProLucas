package br.utfpr.ppgi.perguntaprolucas.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.val;

@Data
@Entity
public class Pergunta {

  @Id private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(50) default 'QUESTAO'")
  private TipoPergunta tipoPergunta;

  @Column(length = 4000)
  private String texto;

  @Enumerated(EnumType.STRING)
  private Dificuldade dificuldade;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta", orphanRemoval = true)
  private List<Opcao> opcoes;

  @Transient private Opcao opcaoSelecionada;

  @Builder
  public Pergunta(
      TipoPergunta tipoPergunta,
      String texto,
      Dificuldade dificuldade,
      Categoria categoria,
      @Singular("opcao") List<Opcao> opcoes) {
    this.tipoPergunta = tipoPergunta;
    this.texto = texto;
    this.dificuldade = dificuldade;
    this.categoria = categoria;
    this.opcoes = opcoes;
  }

  protected Pergunta() {
    // required by JPA
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
