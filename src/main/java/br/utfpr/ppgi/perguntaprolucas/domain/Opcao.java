package br.utfpr.ppgi.perguntaprolucas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Opcao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String texto;

  private boolean correto;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pergunta_id")
  private Pergunta pergunta;

}
