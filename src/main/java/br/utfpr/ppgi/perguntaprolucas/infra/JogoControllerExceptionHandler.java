package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.JogoException;
import br.utfpr.ppgi.perguntaprolucas.web.JogoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JogoControllerExceptionHandler {

    @ExceptionHandler({JogoException.class})
    public ResponseEntity<JogoResponseDto> handleException(JogoException exception) {
        return ResponseEntity.ok().body(JogoResponseDto.builder().fimDoJogo(true).build());
    }

}
