package br.utfpr.ppgi.perguntaprolucas.infra;

import br.utfpr.ppgi.perguntaprolucas.domain.Dificuldade;
import br.utfpr.ppgi.perguntaprolucas.domain.Pergunta;
import br.utfpr.ppgi.perguntaprolucas.domain.PerguntaService;
import org.springframework.stereotype.Service;

@Service
public class PerguntaServiceImpl implements PerguntaService {

    @Override
    public Pergunta proximaPergunta(Dificuldade dificuldade) {
        return null;
    }

}
