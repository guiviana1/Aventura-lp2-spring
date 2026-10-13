package lp2.aventura.spring.service;

import java.util.ArrayList;
import java.util.List;
import lp2.aventura.spring.model.Inimigo;
import lp2.aventura.spring.model.Partida;
import lp2.aventura.spring.model.PartidaEncerradaException;
import lp2.aventura.spring.model.Personagem;
import org.springframework.stereotype.Service;

// Regras de combate por turno
@Service
public class BatalhaService {
    private final MissaoService missaoService;

    public BatalhaService(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    public void garantirEmAndamento(Partida partida) {
        if (partida.isEncerrada()) {
            throw new PartidaEncerradaException("A partida " + partida.getId() + " já foi encerrada.");
        }
    }

    public List<String> turnoDeAtaque(Partida partida) {
        garantirEmAndamento(partida);
        Personagem heroi = partida.getHeroi();
        Inimigo inimigo = partida.getInimigoAtual();
        List<String> eventos = new ArrayList<>();
        eventos.add(heroi.atacar(inimigo));

        if (!inimigo.isVivo()) {
            eventos.add(inimigo.getNome() + " foi derrotado.");
            eventos.addAll(missaoService.concluir(partida.missaoDe(inimigo), heroi));
            partida.avancarInimigo();
            if (partida.isEncerrada()) {
                eventos.add("AVENTURA CONCLUÍDA! Parabéns, aventureiro!");
            } else {
                eventos.add("Você encontrou: " + partida.getInimigoAtual().getNome());
            }
            return eventos;
        }

        eventos.addAll(revideDoInimigo(partida));
        return eventos;
    }

    public List<String> revideDoInimigo(Partida partida) {
        Personagem heroi = partida.getHeroi();
        Inimigo inimigo = partida.getInimigoAtual();
        List<String> eventos = new ArrayList<>(inimigo.atacar(heroi));
        eventos.add("Vida de " + heroi.getNome() + ": " + heroi.getVida());
        eventos.add("Vida de " + inimigo.getNome() + ": " + inimigo.getVida());
        if (!heroi.isVivo()) {
            partida.encerrar();
            eventos.add(heroi.getNome() + " foi derrotado. Fim da aventura.");
        }
        return eventos;
    }
}
