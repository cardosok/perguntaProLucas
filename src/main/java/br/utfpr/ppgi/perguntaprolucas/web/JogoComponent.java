package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import br.utfpr.ppgi.perguntaprolucas.domain.Categoria;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaServiceMockImpl;
import br.utfpr.ppgi.perguntaprolucas.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JogoComponent {

  private App app;

  public void criarJogo(String nome) {
    log.info("Criando o novo jogo para {}", nome);
    this.app =
        App.builder()
            .categoriaSelecionada(new Categoria("Teste e Validação de Software"))
            .perguntaService(new PerguntaServiceMockImpl())
            .usuario(new Usuario(nome))
            .build();
  }

  public String getSituacaoAtual() {
    return "Status: "
        + "\tUsuário atual........: "
        + this.app.getUsuario().getNome()
        + "\tPontos: .............: "
        + this.app.getPontos()
        + "\tRespostas corretas...: "
        + this.app.getRespostasCorretas()
        + "\tRespostas erradas....: "
        + this.app.getRespostasErradas()
        + "\tNível................: "
        + this.app.getDificuldadeAtual()
        + "\n";
  }
}
