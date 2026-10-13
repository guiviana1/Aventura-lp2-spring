package lp2.aventura.spring.controller;

import java.util.Map;
import lp2.aventura.spring.model.ItemNaoEncontradoException;
import lp2.aventura.spring.model.ItemNaoUsavelException;
import lp2.aventura.spring.model.PartidaEncerradaException;
import lp2.aventura.spring.service.PartidaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler({PartidaNaoEncontradaException.class, ItemNaoEncontradoException.class})
    public ResponseEntity<Map<String, String>> naoEncontrado(Exception e) {
        return resposta(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ItemNaoUsavelException.class)
    public ResponseEntity<Map<String, String>> itemNaoUsavel(Exception e) {
        return resposta(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(PartidaEncerradaException.class)
    public ResponseEntity<Map<String, String>> partidaEncerrada(Exception e) {
        return resposta(HttpStatus.CONFLICT, e);
    }

    private ResponseEntity<Map<String, String>> resposta(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(Map.of("erro", e.getMessage()));
    }
}
