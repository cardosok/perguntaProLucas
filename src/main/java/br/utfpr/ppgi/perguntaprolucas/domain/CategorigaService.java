package br.utfpr.ppgi.perguntaprolucas.domain;

import java.util.List;

public interface CategorigaService {
  Categoria getCategoria(Integer id);

  List<Categoria> getCategorias();
}
