package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.CategorigaService;
import br.utfpr.ppgi.perguntaprolucas.domain.JogoException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategorigaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public Categoria getCategoria(Integer id) {
    return this.categoriaRepository
        .findById(id)
        .orElseThrow(() -> new JogoException(String.format("Categoria #%d não encontrada", id)));
  }

  @Override
  public List<Categoria> getCategorias() {
    return this.categoriaRepository.findAll();
  }
}
