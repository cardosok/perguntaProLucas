package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.val;

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
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pergunta {

  @Id
  private Integer id;

  @Column(length = 4000)
  private String texto;

  @Enumerated(EnumType.STRING)
  private Dificuldade dificuldade;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @Singular("opcao")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta", orphanRemoval = true)
  private List<Opcao> opcoes;

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
}
