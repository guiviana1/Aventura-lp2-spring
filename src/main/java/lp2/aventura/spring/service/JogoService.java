package lp2.aventura.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lp2.aventura.spring.model.Esqueleto;
import lp2.aventura.spring.model.Goblin;
import lp2.aventura.spring.model.Inimigo;
import lp2.aventura.spring.model.ItemNaoEncontradoException;
import lp2.aventura.spring.model.ItemNaoUsavelException;
import lp2.aventura.spring.model.Missao;
import lp2.aventura.spring.model.Partida;
import lp2.aventura.spring.model.Personagem;
import org.springframework.stereotype.Service;

// Cria e controla as partidas
@Service
public class JogoService {
    private final Map<Long, Partida> partidas = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    private final PersonagemService personagemService;
    private final MissaoService missaoService;
    private final BatalhaService batalhaService;

    public JogoService(PersonagemService personagemService, MissaoService missaoService,
                       BatalhaService batalhaService) {
        this.personagemService = personagemService;
        this.missaoService = missaoService;
        this.batalhaService = batalhaService;
    }

    public Partida criarPartida(String nomeHeroi) {
        String nome = (nomeHeroi == null || nomeHeroi.isBlank()) ? "Kael" : nomeHeroi.trim();
        Personagem heroi = personagemService.criarHeroi(nome);
        Inimigo goblin = new Goblin("Goblin da Floresta", 30, 5);
        Inimigo esqueleto = new Esqueleto("Esqueleto da Cripta", 70, 15);
        List<Missao> missoes = missaoService.criarMissoesIniciais(goblin, esqueleto);

        Partida partida = new Partida(proximoId.getAndIncrement(), heroi, List.of(goblin, esqueleto), missoes);
        partida.registrarEventos(List.of(
            "=== AVENTURA SOLO ===",
            "Você encontrou: " + goblin.getNome()));
        partidas.put(partida.getId(), partida);
        return partida;
    }

    public List<Partida> listarPartidas() {
        return new ArrayList<>(partidas.values());
    }

    public Partida buscar(long id) {
        Partida partida = partidas.get(id);
        if (partida == null) {
            throw new PartidaNaoEncontradaException(id);
        }
        return partida;
    }

    public Partida atacar(long id) {
        Partida partida = buscar(id);
        partida.registrarEventos(batalhaService.turnoDeAtaque(partida));
        return partida;
    }

    public Partida usarItem(long id, int posicao) throws ItemNaoEncontradoException, ItemNaoUsavelException {
        Partida partida = buscar(id);
        batalhaService.garantirEmAndamento(partida);
        List<String> eventos = new ArrayList<>();
        eventos.add(personagemService.usarItem(partida.getHeroi(), posicao));
        eventos.addAll(batalhaService.revideDoInimigo(partida));
        partida.registrarEventos(eventos);
        return partida;
    }

    public Partida equipar(long id, int posicao) throws ItemNaoEncontradoException, ItemNaoUsavelException {
        Partida partida = buscar(id);
        batalhaService.garantirEmAndamento(partida);
        partida.registrarEventos(List.of(personagemService.equipar(partida.getHeroi(), posicao)));
        return partida;
    }
}
