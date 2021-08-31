package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Data;

import java.util.List;

@Data
public class Pergunta {

    private String texto;

    private Dificuldade dificuldade;

    private List<Opcao> opcao;

}
