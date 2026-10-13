package lp2.aventura.spring.service;

public class PartidaNaoEncontradaException extends RuntimeException {
    public PartidaNaoEncontradaException(long id) {
        super("Partida " + id + " não encontrada.");
    }
}
