package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.App;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JogoAtivoComponent {

  private final Map<String, App> jogosAtivos = new HashMap<>();

  public void putJogo(String uuid, App app) {
    app.setUuid(uuid);
    this.jogosAtivos.put(uuid, app);
  }

  public App getJogo(String uuid) {
    return this.jogosAtivos.get(uuid);
  }
}
