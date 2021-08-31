package br.utfpr.ppgi.perguntaProLucas.domain;

import lombok.Data;

@Data
public class App {

    private Usuario usuario;

    private Integer pontos;

    private Integer perguntasRespondidas;

}
