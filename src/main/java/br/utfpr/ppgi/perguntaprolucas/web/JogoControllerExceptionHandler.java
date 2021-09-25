package br.utfpr.ppgi.perguntaprolucas.web;

import br.utfpr.ppgi.perguntaprolucas.domain.JogoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class JogoControllerExceptionHandler {

  @ExceptionHandler({JogoException.class})
  public ResponseEntity<JogoResponseDto> handleException(JogoException exception) {
    log.error("Pau!", exception);
    return ResponseEntity.ok().body(JogoResponseDto.builder().fimDoJogo(true).build());
  }
}
